import React from "react";

function NavigationBar() {
  return (
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <a class="navbar-brand">Two For Tom Open</a>

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
