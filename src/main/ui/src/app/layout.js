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
      </body>
    </html>
  );
}
