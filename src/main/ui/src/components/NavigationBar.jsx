import Link from 'next/link';

function NavigationBar() {
  return (
    <div>
      <nav className='navbar navbar-expand-md navbar-light bg-light'>
        <a href='/' className='navbar-brand'>
          Two For Tom Open
        </a>
        <button
          className='navbar-toggler'
          type='button'
          data-toggle='collapse'
          data-target='#navbarNav'
          aria-controls='navbarNav'
          aria-expanded='false'
          aria-label='Toggle navigation'
        >
          <span className='navbar-toggler-icon'></span>
        </button>

        <div className='collapse navbar-collapse' id='navbarNav'>
          <ul className='navbar-nav'>
            <li className='nav-item'>
              <Link className='nav-link' href='/'>
                Home
              </Link>
            </li>
            <li className='nav-item'>
              <Link className='nav-link' href='/tournament'>
                Tournaments
              </Link>
            </li>
            <li className='nav-item'>
              <Link className='nav-link' href='/player'>
                Players
              </Link>
            </li>
          </ul>
        </div>
      </nav>
    </div>
  );
}

export default NavigationBar;
