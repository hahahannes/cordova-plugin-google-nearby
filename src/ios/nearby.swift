@objc(ModusEchoSwift) class ModusEchoSwift : CDVPlugin {
  func publish() {
     let publication = messageManager.publication(with: GNSMessage(content: name.data(using: .utf8)))
  }

  func subscribe() {
    let subscription =
    messageManager.subscription(messageFoundHandler: { (message: GNSMessage?) in
      // Add the name to a list for display
    },
    messageLostHandler: { (message: GNSMessage?) in
      // Remove the name from the list
    })
  }

  @objc(echo:)
  func echo(command: CDVInvokedUrlCommand) {
    var pluginResult = CDVPluginResult(
      status: CDVCommandStatus_ERROR
    )

    let msg = command.arguments[0] as? String ?? ""

    if msg.characters.count > 0 {
      let toastController: UIAlertController =
        UIAlertController(
          title: "",
          message: msg,
          preferredStyle: .alert
        )
      
      self.viewController?.present(
        toastController,
        animated: true,
        completion: nil
      )

      DispatchQueue.main.asyncAfter(deadline: .now() + 3) {
        toastController.dismiss(
          animated: true,
          completion: nil
        )
      }
        
      pluginResult = CDVPluginResult(
        status: CDVCommandStatus_OK,
        messageAs: msg
      )
    }

    self.commandDelegate!.send(
      pluginResult,
      callbackId: command.callbackId
    )
  }
}
