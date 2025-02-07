package com.emretest.exception;

public class ErrorMessage {
    private MessageType messageType;
    private String ofStatic;

    public String prepareErrorMessage() {
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append(messageType.getMessage());
        if (ofStatic != null) {
            errorMessage.append(" : ").append(ofStatic);
        }
        return errorMessage.toString();
    }

    // Getter ve Setter metotları
    public MessageType getMessageType() { return messageType; }
    public void setMessageType(MessageType messageType) { this.messageType = messageType; }

    public String getOfStatic() { return ofStatic; }
    public void setOfStatic(String ofStatic) { this.ofStatic = ofStatic; }
}
