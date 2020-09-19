package io.github.q843705423.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ScoreNode {
    private String name;
    private Integer score;
    private List<ScoreNode> nodeList;

    public ScoreNode(String name) {
        this.name = name;

    }

    public void add(ScoreNode node) {
        if (nodeList == null) {
            nodeList = new ArrayList<>();
        }
        this.nodeList.add(node);

    }

    @Override
    public String toString() {
        return "";
    }
}

