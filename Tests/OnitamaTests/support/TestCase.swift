import Foundation
import XCTest

class TestCase: XCTestCase {
  // -- lifecycle --
  override func setUp() {
    super.setUp()
    self.continueAfterFailure = false
  }
}
