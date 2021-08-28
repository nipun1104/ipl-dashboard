
import './HomePage.scss'
import { React, useEffect, useState } from 'react';
import {TeamTitle} from '../components/TeamTitle'

export const HomePage = () => {

    const [teams, setTeams] = useState([]);

    //const { teamName } = useParams();

    useEffect(
        () => {
            const fetchAllTeams = async () => {
                const response = await fetch('http://localhost:8080/teams');
                const data = await response.json();
                setTeams(data);
            };
            fetchAllTeams();
        }, []
    );

    return (
        <div className="HomePage">
            <div className="header-section">
                <h1 className="app-name">IPL Dashboard</h1>
            </div>
            <div className="team-grid">
                {teams.map(team => <TeamTitle teamName={team.teamName}/>)}
            </div>
        </div>
    );
}
