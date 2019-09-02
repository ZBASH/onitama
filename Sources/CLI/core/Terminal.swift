import Darwin.POSIX.termios
import Foundation

class Terminal {
  enum Code {
    static let meta1: UInt8 = 27
    static let meta2: UInt8 = 91
    static let left: UInt8 = 68
    static let right: UInt8 = 67
    static let enter: UInt8 = 10
    static let q: UInt8 = 113
  }

  // -- properties --
  private var rawTerm: termios?

  private let input: FileHandle = .standardInput
  private let output: FileHandle = .standardOutput

  // -- lifetime --
  init() {
  }

  // -- io --
  // -- io/commands
  func write(_ text: String) {
    print(text, terminator: "")
  }

  func writeln(_ text: String = "") {
    print(text)
  }
  
  func exit() {
    Darwin.exit(0)
  }

  // -- raw --
  var isRaw: Bool {
    get {
      return rawTerm != nil
    }
    set {
      if newValue == isRaw {
        return
      } else if newValue {
        enableRawInput()
      } else {
        disableRawInput()
      }
    }
  }

  // -- raw/queries
  func read() -> [UInt8] {
    isRaw = true
    defer {
      isRaw = false
    }

    var buffer: [UInt8] = []

    var char: UInt8 = 0
    while Darwin.read(input.fileDescriptor, &char, 1) == 1 {
      buffer.append(char)

      // arrow keys are a sequence of three characters, 239 && 156 are
      // the first two control characters in that sequence.
      if char != Code.meta1 && char != Code.meta2 {
        break
      }
    }

    return buffer
  }

  // -- raw/commands
  private func enableRawInput() {
    let pointer = UnsafeMutablePointer<termios>.allocate(capacity: 1)
    var termios = pointer.pointee
    pointer.deallocate()

    tcgetattr(input.fileDescriptor, &termios)
    rawTerm = termios

    termios.c_lflag &= ~(UInt(ECHO | ICANON))
    tcsetattr(input.fileDescriptor, TCSAFLUSH, &termios)
  }

  private func disableRawInput() {
    guard var term = rawTerm else {
      return
    }

    tcsetattr(input.fileDescriptor, TCSAFLUSH, &term)
    rawTerm = nil
  }
}
