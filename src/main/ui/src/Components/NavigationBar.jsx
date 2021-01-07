import React from "react";

function NavigationBar() {
  return (
    <nav class="navbar navbar-expand-md navbar-light bg-light">
      <a class="navbar-brand">Two For Tom Open</a>
      <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarNav"
        aria-controls="navbarNav"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item active">
            <a class="nav-link" href="#">
              Home
            </a>
          </li>

          <li class="nav-item">
            <a class="nav-link" href="#">
              Tournaments
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              Players
            </a>
          </li>
        </ul>
      </div>
    </nav>
  );
}

export default NavigationBar;
