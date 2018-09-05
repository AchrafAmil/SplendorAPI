package com.neogineer.splendorapi.api.data;

import com.neogineer.splendorapi.api.ReadOnlyPlayerState;

public abstract class Transaction {

    public TransactionType mType;

    public abstract boolean canBeIssuedBy(ReadOnlyPlayerState playerState);
}
