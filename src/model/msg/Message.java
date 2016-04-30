package model.msg;


/**
 * Abstract class to model a Message
 *
 * @author Conor
 */
public abstract class Message {
    private String senderId = "";
    private String receiverId = "";

    /**
     * Returns the senderId
     *
     * @return senderId
     */

    public String getSenderId() {
        return senderId;
    }

    /**
     * Sets the senderId
     *
     * @param sendId The string to be set
     */

    public void setSenderId(String sendId) {
        this.senderId = sendId;
    }

    /**
     * Returns the receiverId
     *
     * @return receiverId
     */

    public String getReceiverId() {
        return receiverId;
    }

    /**
     * Sets the receiverId
     *
     * @param receiverId The string to be set
     */

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (this instanceof Atom && object instanceof Atom) {
            return ((Atom) this).getVarName().equals(((Atom) object).getVarName());
        }
        if (this instanceof Xor && object instanceof Xor) {
            return ((Xor) this).getMessage1().equals(((Xor) object).getMessage1()) && ((Xor) this).getMessage2().equals(((Xor) object).getMessage2());
        }
        if (this instanceof UnDecryptable && object instanceof UnDecryptable) {
            return ((UnDecryptable) this).getMessage().equals(((UnDecryptable) object).getMessage()) && ((UnDecryptable) this).getVarName().equals(((UnDecryptable) object).getVarName());
        }
        if (this instanceof Forward && object instanceof Forward) {
            return ((Forward) this).getMessage().equals(((Forward) object).getMessage()) && ((Forward) this).getVarName().equals(((Forward) object).getVarName());
        }
        if (this instanceof Encrypt && object instanceof Encrypt) {

            return ((Encrypt) this).getKey().equals(((Encrypt) object).getKey()) && ((Encrypt) this).getMessageList().equals(((Encrypt) object).getMessageList());
        }
        if (this instanceof MessageList && object instanceof MessageList) {
            if (((MessageList) this).getMessageList().size() != ((MessageList) object).getMessageList().size()) {
                return false;
            }
            boolean flag = true;
            for (int i = 0; i < ((MessageList) this).getMessageList().size(); i++) {
                flag = ((MessageList) this).getMessageList().get(i).equals(((MessageList) object).getMessageList().get(i));
                if (!flag) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
