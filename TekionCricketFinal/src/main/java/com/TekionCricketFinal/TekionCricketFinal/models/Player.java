package com.TekionCricketFinal.TekionCricketFinal.models;
import com.TekionCricketFinal.TekionCricketFinal.models.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
public class Player {
    private String name;
    private Role role;
    private int runs = 0;
    private int wickets = 0;
    private int balls = 0;

    public Player(String name, Role role) {
        this.name = name;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
