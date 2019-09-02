extension Array {
  /// `grid` repeats an element in a rectangular grid with width and height
  /// equal to `count`.
  static public func grid(
    repeating element: Element,
    count: Int
  ) -> [[Element]] {
    return [[Element]](
      repeating: Array(repeating: element, count: count),
      count: count
    )
  }
}
