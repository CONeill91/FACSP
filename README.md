### What is this repository for? ###

FACSP is research tool used to formally verify cryptographic security protocols with respect to security properties.
The idea for the project came from the Dependable Systems Research group in DCU.

The application takes as input, a protocol specified in Casper syntax (more info on Casper here:  http://www.cs.ox.ac.uk/gavin.lowe/Security/Casper/).

This script is then parsed using javaCC and analysed for vulnerabilities.

The application contains a built in editor where a user can specify their own protocols and security properties.

Any vulnerabilities exposed by the analysis are highlighted to the user along with path an attacker must take to obtain forbidden data.

### Who do I talk to? ###

Conor O'Neill
conor.oneill63@mail.dcu.ie