package com.exorath.exoteams.startRule;

import com.exorath.exoteams.player.TeamPlayerJoinTeamEvent;
import com.exorath.exoteams.player.TeamPlayerLeaveTeamEvent;

/**
 * Created by toonsev on 6/4/2017.
 */
public class GlobalMinPlayersStartRule extends GlobalStartRule {
    private boolean canStart = false;
    private int players;
    private int minPlayers;

    public GlobalMinPlayersStartRule(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    @Override
    public String getReason() {
        return "Insufficient players";
    }

    @Override
    public void onPlayerJoin(TeamPlayerLeaveTeamEvent event) {
        addPlayers(1);
        evaluate();
    }

    @Override
    public void onPlayerJoin(TeamPlayerJoinTeamEvent event) {
        addPlayers(-1);
        evaluate();
    }

    private synchronized void addPlayers(int amount) {
        players = players + amount;
    }

    @Override
    public boolean doEvaluate() {
        return players >= minPlayers;
    }
}