package com.example.serene.media;

public interface PackableEx extends Packable {
    void unmarshal(ByteBuf in);
}
