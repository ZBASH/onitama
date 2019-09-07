import Foundation

enum CardData {
  static var data: Data {
    guard let data = str.data(using: .utf8) else {
      fatalError("This hardcoded string is not valid utf-8")
    }

    return data
  }

  static let str
    = """
    {
      "cards": [
        {
          "name": "Crane",
          "points": [
            { "x": 0, "y": 1 },
            { "x": -1, "y": -1 },
            { "x": 1, "y": -1 }
          ]
        },
        {
          "name": "Rabbit",
          "points": [
            { "x": 1, "y": 1 },
            { "x": 2, "y": 0 },
            { "x": -1, "y": -1 }
          ]
        },
        {
          "name": "Mantis",
          "points": [
            { "x": -1, "y": 1 },
            { "x": 1, "y": 1 },
            { "x": 0, "y": -1 }
          ]
        },
        {
          "name": "Dragon",
          "points": [
            { "x": -2, "y": 1 },
            { "x": 2, "y": 1 },
            { "x": -1, "y": -1 },
            { "x": 1, "y": 1 }
          ]
        },
        {
          "name": "Tiger",
          "points": [
            { "x": 0, "y": 2 },
            { "x": 0, "y": -1 }
          ]
        },
        {
          "name": "Rooster",
          "points": [
            { "x": 1, "y": 1 },
            { "x": -1, "y": 0 },
            { "x": 1, "y": 0 },
            { "x": -1, "y": -1 }
          ]
        },
        {
          "name": "Frog",
          "points": [
            { "x": -1, "y": -1 },
            { "x": -2, "y": 0 },
            { "x": 1, "y": -1 }
          ]
        },
        {
          "name": "Horse",
          "points": [
            { "x": 0, "y": 1 },
            { "x": -1, "y": 0 },
            { "x": 0, "y": -1 }
          ]
        },
        {
          "name": "Boar",
          "points": [
            { "x": 0, "y": 1 },
            { "x": -1, "y": 0 },
            { "x": 1, "y": 0 }
          ]
        },
        {
          "name": "Monkey",
          "points": [
            { "x": -1, "y": 1 },
            { "x": 1, "y": 1 },
            { "x": -1, "y": -1 },
            { "x": 1, "y": -1 }
          ]
        },
        {
          "name": "Ox",
          "points": [
            { "x": 0, "y": 1 },
            { "x": 1, "y": 0 },
            { "x": 0, "y": -1 }
          ]
        },
        {
          "name": "Cobra",
          "points": [
            { "x": 1, "y": 1 },
            { "x": -1, "y": 0 },
            { "x": 1, "y": -1 }
          ]
        },
        {
          "name": "Eel",
          "points": [
            { "x": -1, "y": 1 },
            { "x": 1, "y": 0 },
            { "x": -1, "y": -1 }
          ]
        },
        {
          "name": "Crab",
          "points": [
            { "x": 0, "y": 1 },
            { "x": -2, "y": 0 },
            { "x": 2, "y": 0 }
          ]
        },
        {
          "name": "Goose",
          "points": [
            { "x": -1, "y": 1 },
            { "x": -1, "y": 0 },
            { "x": 1, "y": 0 },
            { "x": 1, "y": -1 }
          ]
        },
        {
          "name": "Elephant",
          "points": [
            { "x": -1, "y": 1 },
            { "x": 1, "y": 1 },
            { "x": -1, "y": 0 },
            { "x": 1, "y": 0 }
          ]
        }
      ]
    }
    """
}
