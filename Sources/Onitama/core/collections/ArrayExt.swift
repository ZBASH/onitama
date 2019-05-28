extension Array {
  static func grid(repeating element: Element, count: Int) -> [[Element]] {
    return Array<[Element]>(repeating: Array(repeating: element, count: count), count: count)
  }
}
