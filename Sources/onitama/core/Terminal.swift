import Foundation
import Darwin.POSIX.termios

class Terminal {
  // properties
  private var rawTerm: termios?
  private let input  = FileHandle.standardInput
  private let output = FileHandle.standardOutput
  
  // raw
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
  
  // raw/queries
  func read() -> UInt8? {
    var char: UInt8 = 0
    
    if Darwin.read(input.fileDescriptor, &char, 1) == 1 {
      return char
    } else {
      return nil
    }
  }
  
  // raw/commands
  private func enableRawInput() {
    let pointer = UnsafeMutablePointer<termios>.allocate(capacity: 1)
    var termios = pointer.pointee
    pointer.deallocate()
  
    tcgetattr(input.fileDescriptor, &termios)
    rawTerm = termios
    
    termios.c_lflag &= ~(UInt(ECHO | ICANON))
    tcsetattr(input.fileDescriptor, TCSAFLUSH, &termios);
  }
  
  private func disableRawInput() {
    guard var term = rawTerm else {
      return
    }
    
    tcsetattr(input.fileDescriptor, TCSAFLUSH, &term);
    rawTerm = nil
  }
}
//
//var char: UInt8 = 0
//while read(stdIn.fileDescriptor, &char, 1) == 1 {
//  if char == 0x04 { // detect EOF (Ctrl+D)
//    break
//  }
//
//  print(char)
//}
//
//// It would be also nice to disable raw input when exiting the app.
//restoreRawMode(fileHandle: stdIn, originalTerm: originalTerm)
//
