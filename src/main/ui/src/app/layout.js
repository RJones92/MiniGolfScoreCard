// Bootstrap imports
import 'bootstrap/dist/css/bootstrap.min.css';
import BootstrapClient from '../components/BootstrapClient';

// Style imports
import '../global.css';

// Other imports
import NavigationBar from '../components/NavigationBar';

export const metadata = {
  title: 'Minigolf App',
  description: 'Minigolf score application',
};

export default function RootLayout({ children }) {
  return (
    <html lang='en'>
      <body>
        <NavigationBar />
        <div id='root'>{children}</div>
        <BootstrapClient />
      </body>
    </html>
  );
}
