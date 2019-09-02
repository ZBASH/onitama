import XCTest
import Foundation

class TestCase: XCTestCase {
  // -- lifecycle --
  override func setUp() {
    super.setUp()
    self.continueAfterFailure = false
  }
}
