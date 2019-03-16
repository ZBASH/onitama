import Onitama

print("Hello, world!")

let terminal = Onitama.Terminal()

terminal.isRaw = true

while true {
  guard let char = terminal.read(), char != 0x04 else {
    break
  }

  print("\(char)")
}

terminal.isRaw = false
