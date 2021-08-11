
import { React } from 'react';
import { Link } from 'react-router-dom';

export const MatchSmallCard = ({ match, teamName }) => {
    if (!match) return null;
    const otherTeam = teamName === match.team1 ? match.team2 : match.team1;
    const otherTeamRoute = `/teams/${otherTeam}`;
    return (
        <div className="MatchSmallCard">
            <h3>vs
                <Link to={otherTeamRoute}>{otherTeam}</Link></h3>
            <p>{match.matchWinner} won by {match.resultMargin} {match.result}</p>
        </div>
    );
}
