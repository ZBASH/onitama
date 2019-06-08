// swift-tools-version:4.2
// The swift-tools-version declares the minimum version of Swift required to build this package.

import PackageDescription

let package = Package(
  name: "Onitama",
  products: [
    .executable(
      name: "OnitamaCLI",
      targets: [
        "CLI",
      ]
    ),
    .library(
      name: "Onitama",
      targets: [
        "Onitama",
      ]
    ),
  ],
  dependencies: [
  ],
  targets: [
    .target(
      name: "CLI",
      dependencies: [
        "Onitama",
      ]
    ),
    .target(
      name: "Onitama",
      dependencies: [
      ]
    ),
    .testTarget(
      name: "OnitamaTests",
      dependencies: [
        "Onitama",
      ]
    ),
  ]
)
