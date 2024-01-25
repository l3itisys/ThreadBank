# Multi-Threaded Web Server for Bank Transfers

## Overview
This project features a multi-threaded web server in Java, designed to handle bank transfer operations with up to 1000 concurrent threads. It includes both server and client implementations in Java, along with Python scripts for integration and load testing.

## Features
- Handles up to 1000 concurrent threads using a thread pool.
- Implements GET and POST HTTP requests.
- Manages bank accounts and transfers with thread-safe operations.
- Includes comprehensive unit, integration, and load tests.

## Setup and Running
1. Clone the repository.
2. Compile Java code in `src` directory (`javac server/WebServer.java client/SimpleWebClient.java`).
3. Run the server (`java server.WebServer`).
4. Test bank transfers using the client (`java client.SimpleWebClient`).

## Testing
- Perform unit tests within `test/java`.
- Run `integration_test.py` and `load_test.py` for integration and load testing.

## Contributing
Contributions are welcome. Please read `CONTRIBUTING.md` for guidelines on contributing.

## License
This project is under MIT License. See `LICENSE` for more information.
