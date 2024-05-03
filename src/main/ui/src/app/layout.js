// Bootstrap imports
import 'bootstrap/dist/css/bootstrap.min.css';
import BootstrapClient from '../components/BootstrapClient';

// Style imports
import '../index.css';

export const metadata = {
  title: 'Minigolf App',
  description: 'Minigolf score application',
};

export default function RootLayout({ children }) {
  return (
    <html lang='en'>
      <body>
        <div id='root'>{children}</div>
        <BootstrapClient />
      </body>
    </html>
  );
}
