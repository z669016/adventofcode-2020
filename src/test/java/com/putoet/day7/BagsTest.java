package com.putoet.day7;

import com.putoet.resources.ResourceLines;
import org.junit.jupiter.api.Test;

class BagsTest {

    @Test
    void loadBags() {
        Bags.loadBags(ResourceLines.list("/day7.txt"));
    }
}