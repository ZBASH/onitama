import XCTest

#if !os(macOS)
  /// It's all the tests.
  public func allTests() -> [XCTestCaseEntry] {
    return [
      testCase(PointTests.allTests),
    ]
  }
#endif
