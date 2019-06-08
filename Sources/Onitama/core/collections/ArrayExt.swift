extension Array {
  /// Repeats an element in a rectangular grid with width and heigh equal to `count`.
  static func grid(repeating element: Element, count: Int) -> [[Element]] {
    return Array<[Element]>(
      repeating: Array(repeating: element, count: count),
      count: count
    )
  }
}
