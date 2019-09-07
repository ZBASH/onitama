do {
  let app = try App()
  app.start()
} catch {
  debugPrint("unhandled error: \(error)")
}
