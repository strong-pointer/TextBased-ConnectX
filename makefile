default: src/extendedConnectX/IGameBoard.java src/extendedConnectX/AbsGameBoard.java src/extendedConnectX/BoardPosition.java src/extendedConnectX/GameBoard.java src/extendedConnectX/GameBoardMem.java src/extendedConnectX/GameScreen.java
	javac src/extendedConnectX/IGameBoard.java src/extendedConnectX/AbsGameBoard.java src/extendedConnectX/BoardPosition.java src/extendedConnectX/GameBoard.java src/extendedConnectX/GameBoardMem.java src/extendedConnectX/GameScreen.java

run: src/extendedConnectX/IGameBoard.class src/extendedConnectX/AbsGameBoard.class src/extendedConnectX/BoardPosition.class src/extendedConnectX/GameBoard.class src/extendedConnectX/GameScreen.java
	java src.extendedConnectX.GameScreen

clean:
	rm -f src/extendedConnectX/*.class