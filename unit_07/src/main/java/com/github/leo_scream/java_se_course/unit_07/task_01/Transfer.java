package com.github.leo_scream.java_se_course.unit_07.task_01;

import java.util.Objects;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class Transfer {
    private final Account sender;
    private final Account recipient;
    private final long amount;

    private Transfer(final Account sender, final Account recipient, final long amount) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
    }

    public Account getSender() {
        return sender;
    }

    public Account getRecipient() {
        return recipient;
    }

    public long getAmount() {
        return amount;
    }

    public Transfer flip() {
        return new Transfer(recipient, sender, amount);
    }

    public Transfer withAmount(final long amount) {
        return new Transfer(sender, recipient, amount);
    }

    public Transfer withRecipient(final Account recipient) {
        Objects.requireNonNull(recipient);
        return new Transfer(sender, recipient, amount);
    }
}
