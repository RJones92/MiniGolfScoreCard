import React from "react";
import { Route, NavLink, HashRouter } from "react-router-dom";
import HomePage from "./HomePage";
import TournamentPage from "./TournamentPage";
import PlayerPage from "./PlayerPage";

function NavigationBar() {
  return (
    <div>
      <HashRouter>
        <nav className="navbar navbar-expand-md navbar-light bg-light">
          <a className="navbar-brand">Two For Tom Open</a>
          <button
            className="navbar-toggler"
            type="button"
            data-toggle="collapse"
            data-target="#navbarNav"
            aria-controls="navbarNav"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>

          <div className="collapse navbar-collapse" id="navbarNav">
            <ul className="navbar-nav">
              <li className="nav-item active">
                <NavLink className="nav-link" exact to="/">
                  Home
                </NavLink>
              </li>
              <li className="nav-item">
                <NavLink className="nav-link" to="/tournaments">
                  Tournaments
                </NavLink>
              </li>
              <li className="nav-item">
                <NavLink className="nav-link" to="/players">
                  Players
                </NavLink>
              </li>
            </ul>
          </div>
        </nav>
        <div className="router">
          <Route exact path="/" component={HomePage} />
          <Route path="/tournaments" component={TournamentPage} />
          <Route path="/players" component={PlayerPage} />
        </div>
      </HashRouter>
    </div>
  );
}

export default NavigationBar;
