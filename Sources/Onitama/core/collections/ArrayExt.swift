extension Array {
  /// `at` returns the element at the index, or nil if it is out of bounds.
  public func at(_ index: Int) -> Element? {
    if index < 0 || index >= count {
      return nil
    }

    return self[index]
  }

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
