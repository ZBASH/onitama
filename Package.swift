// swift-tools-version:5.0

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
