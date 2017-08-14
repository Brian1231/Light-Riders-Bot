
/*
 * settings timebank 10000
settings time_per_move 200
settings player_names player0,player1
settings your_bot player0
settings your_botid 0
settings field_width 16
settings field_height 16

update game round 0
update game field .,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,0,.,.,.,.,.,.,.,.,1,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.
action move 10000

//flood test should go up
update game round 21
update game field .,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,x,x,.,.,.,.,.,.,.,.,.,.,.,.,.,x,x,x,x,.,.,.,.,.,.,.,.,x,x,x,x,x,.,x,x,.,.,.,.,.,.,.,.,.,.,.,x,x,x,.,.,.,.,.,.,.,.,.,.,.,.,.,x,.,x,.,.,.,.,.,.,.,.,.,.,.,.,.,x,.,x,.,.,.,.,.,.,.,.,.,.,.,.,.,x,.,x,x,x,.,.,.,.,.,.,.,.,.,.,.,x,.,x,x,x,x,.,.,.,.,.,.,.,.,.,.,x,.,.,.,1,x,.,.,.,.,.,.,.,.,.,.,x,.,.,.,.,.,0,x,x,x,x,x,x,x,x,x,x
action move 10000

//Alone test
update game round 43
update game field x,x,x,x,x,x,x,x,x,x,x,.,.,.,.,0,x,.,.,.,.,.,.,.,.,.,x,.,.,.,.,x,x,.,.,.,.,.,.,.,.,.,x,.,.,.,.,x,x,.,.,.,.,.,.,.,.,.,x,.,.,.,.,x,x,.,.,.,.,.,.,.,.,.,x,.,.,.,.,x,x,.,.,.,.,.,.,.,.,.,x,.,.,.,.,x,x,x,x,x,x,x,x,x,x,x,x,.,.,.,.,x,x,x,x,x,x,x,.,.,.,x,x,.,.,.,.,x,x,.,.,.,.,.,.,.,.,x,.,.,.,.,.,x,x,.,.,.,.,.,.,.,.,x,.,.,.,.,.,x,x,.,.,.,.,.,.,.,.,x,.,.,.,.,.,x,x,.,.,.,.,.,.,.,.,x,.,.,.,.,.,x,x,.,.,.,.,.,.,.,.,x,.,.,.,.,.,x,x,.,.,.,.,.,.,.,.,x,.,.,.,.,.,x,x,.,.,.,.,.,1,x,x,x,.,.,.,.,.,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x
action move 10000


update game round 2
update game field .,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,0,.,.,.,.,.,.,.,.,1,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.
action move 10000

update game round 1
update game field .,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,1,x,.,.,.,.,0,.,.,.,.,.,.,.,.,.,.,x,.,.,.,.,x,x,.,.,.,.,.,.,.,.,.,x,.,.,.,.,.,x,x,.,.,.,.,.,.,.,.,x,.,.,.,.,.,.,x,x,.,.,.,.,.,.,.,x,x,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.,.
action move 10000

 */
package bot;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
//import java.util.Random;
import java.util.Queue;

import field.Field;
import move.Move;
import move.MoveType;

public class Bri_bot {
	public static final int DIR_UP = 0, DIR_DOWN = 1, DIR_LEFT = 2, DIR_RIGHT = 3;
	int yo = 0;
	Move lastMove;
	/**
	 * @return a Move object
	 */
	//Method called to ask bot to make a move
	public Move doMove(BotState state) {

		//Random rand = new Random();

		//Update bot data
		MoveType lastMoveType = state.getLastDirection();
		lastMove = new Move(lastMoveType);
		Move nextMove;
		Field field = state.getField();
		Point myPos = field.getMyPosition();
		Point enemyPos = field.getEnemyPosition();
		Point nextP = nextPos(myPos, lastMove);
		int roundNumber = state.getRoundNumber();
		//int playerNum = Integer.parseInt(state.getMyName().replaceAll("player", ""));
		//Create objects for possible moves
		Move up = new Move(MoveType.UP);
		Move down = new Move(MoveType.DOWN);
		Move left = new Move(MoveType.LEFT);
		Move right = new Move(MoveType.RIGHT);
		Move pass = new Move(MoveType.PASS);


		/*int floodTestRight = floodUse(myPos.x+1, myPos.y, field);
		System.out.println("Flood Count: " + floodTestRight);
		int floodTestLeft = floodUse(myPos.x-1, myPos.y, field);
		System.out.println("Flood Count: " + floodTestLeft);*/
		//Data testing
		//System.out.println("Round: " + roundNumber);
		//System.out.println("My Position: (" + myPos.x + ", " + myPos.y + ")");

		// First move
		if (roundNumber == 0) {
			//Find nearest wall and head in that direction
			//Calculate distances to walls
			int[] distToWalls = new int[4];
			distToWalls[DIR_UP] = myPos.y; //UP
			distToWalls[DIR_DOWN] = field.getHeight() - myPos.y; //DOWN
			distToWalls[DIR_LEFT] = myPos.x;//LEFT
			distToWalls[DIR_RIGHT] = field.getWidth() - myPos.x; //RIGHT

			//Find shortest distance
			int shortestDist = distToWalls[DIR_LEFT];
			int desiredDirection = DIR_LEFT;
			for(int i=0;i<4;i++){
				if(distToWalls[i] < shortestDist){
					shortestDist = distToWalls[i];
					desiredDirection = i;
				}
			}
			switch(desiredDirection){
			case DIR_UP:
				nextMove = up;
				break;
			case DIR_DOWN:
				nextMove = down;
				break;
			case DIR_LEFT:
				nextMove = left;
				break;
			case DIR_RIGHT:
				nextMove = right;
				break;
			default:
				nextMove = left;
				break;
			}
			//nextMove = new Move(MoveType.getRandomExcluding(MoveType.PASS));
			state.setLastDirection(nextMove.getMoveType());
		}
		//Not first move
		else {

			//Create list of possible moveTypes
			List<Move> possibleMoves = new ArrayList<Move>();

			possibleMoves.add(up);
			possibleMoves.add(down);
			possibleMoves.add(left);
			possibleMoves.add(right);
			possibleMoves.add(pass);

			//-----REMOVE BLOCKED MOVES-----------
			//Remove current and opposite direction
			switch(lastMoveType.getOpposite()){
			case UP:
				possibleMoves.remove(up);
				possibleMoves.remove(down);
			case DOWN:
				possibleMoves.remove(up);
				possibleMoves.remove(down);
				break;
			case LEFT:
				possibleMoves.remove(left);
				possibleMoves.remove(right);
				break;
			case RIGHT:
				possibleMoves.remove(left);
				possibleMoves.remove(right);
				break;
			case PASS:
				break;
			default:
				break;
			}

			//Check if any immediate neighbor square is blocked (wall or edge or enemy player)
			if(!field.isClear(myPos.x, myPos.y-1)) possibleMoves.remove(up);
			if(!field.isClear(myPos.x, myPos.y+1)) possibleMoves.remove(down);
			if(!field.isClear(myPos.x-1, myPos.y)) possibleMoves.remove(left);
			if(!field.isClear(myPos.x+1, myPos.y)) possibleMoves.remove(right);

			//If we're heading straight into a wall, remove "PASS"
			if(possibleMoves.contains(pass)){
				switch (lastMoveType) {
				case UP:
					if(!field.isClear(myPos.x, myPos.y-1)) possibleMoves.remove(pass);
					break;
				case DOWN:
					if(!field.isClear(myPos.x, myPos.y+1)) possibleMoves.remove(pass);
					break;
				case LEFT:
					if(!field.isClear(myPos.x-1, myPos.y)) possibleMoves.remove(pass);
					break;
				case RIGHT:
					if(!field.isClear(myPos.x+1, myPos.y)) possibleMoves.remove(pass);
					break;
				default:

				}
			}

			//Dead end avoidance if heading into a dead end
			if(nextP != null){
				if(possibleMoves.size() >= 2 && field.isDeadEnd(nextP.x, nextP.y)) possibleMoves.remove(pass);
			}

			//-----MOVE SELECTION-----------
			//Are we alone?? If so use greedy algorithm
			/*if(weAreAlone(possibleMoves, myPos, field)){

				//--Greedy method--
				switch(possibleMoves.size()){
				case 0:
					nextMove = pass; //Default to Pass
					break;
				case 1:
					nextMove = possibleMoves.get(0); //Pick only move
					break;
				case 2:
					nextMove = pass;
					break;
				case 3:
					nextMove = pass;
					break;
				default:
					nextMove = pass;
					break;
				}
			}else{*/

				//--Flood-Fill count method--
				switch(possibleMoves.size()){
				case 0:
					nextMove = pass; //Default to Pass
					break;
				case 1:
					nextMove = possibleMoves.get(0); //Pick only move
					break;
				case 2:
					//Use Flood Fill in 2 directions to find largest space
					Move one = possibleMoves.get(0);
					Move two = possibleMoves.get(1);
					Point pOne = nextPos(myPos, one);
					Point pTwo = nextPos(myPos, two);
					int oneCount = floodUse(pOne, field);
					int twoCount = floodUse(pTwo, field);
					//System.out.println(one.getMoveType() + ": " + oneCount);
					//System.out.println(two.getMoveType() + ": " + twoCount);
					//Make decision
					if(oneCount>twoCount) nextMove = one;
					else if(twoCount>oneCount) nextMove = two;
					else if(possibleMoves.contains(pass))nextMove = pass; //Move straight if we can

					//If two moves have same flood fill, then move away from obstacles
					else {
						int distOne = distToObstacle(field, one, myPos);
						int distTwo = distToObstacle(field, two, myPos);
						if(distOne>distTwo) nextMove = one;
						else nextMove = two;
					}
					break;
				case 3:
					//Use flood fill in 3 directions to check if there's a large space to move into
					Move moveOne = possibleMoves.get(0);
					Move moveTwo = possibleMoves.get(1);
					Move moveThree = possibleMoves.get(2);
					Point ptOne = nextPos(myPos, moveOne);
					Point ptTwo = nextPos(myPos, moveTwo);
					Point ptThree = nextPos(myPos, moveThree);
					int countOne = floodUse(ptOne, field);
					int countTwo = floodUse(ptTwo, field);
					int countThree = floodUse(ptThree, field);
					//if not all counts are the same, at a junction
					if(countOne != countTwo || countOne != countThree || countTwo != countThree){
						int biggestSpace = countOne;
						nextMove = moveOne;
						if(countTwo > biggestSpace){
							biggestSpace = countTwo;
							nextMove = moveTwo;
						}
						if(countThree > biggestSpace){
							nextMove = moveThree;
						}
					}
					//out in the open
					else{
						

						//nextMove = pass;
						if(!weAreAlone(possibleMoves, myPos, field)){
							nextMove = voronoi(possibleMoves, myPos, enemyPos, field);

						}else{
							nextMove = pass;
						}

					}
					break;
				default:
					nextMove = pass;
					break;
				}
			}
			if (nextMove.equals(lastMove)) nextMove = pass;

			//Update last direction
			if (!nextMove.equals(pass)) {
				state.setLastDirection(nextMove.getMoveType());
			}

			//System.out.println("Last MoveType: " + lastMoveType);
			//System.out.print("Possible moves: " + possibleMoves + "\n");
			//System.out.println("Facing direction: " + lastMove.getMoveType());
			System.out.println(field);

		//}

		//Return move
		return nextMove;
	}

	//Method to measure distance in a direction to an obstacle
	private int distToObstacle(Field field, Move direction, Point pos){
		int dist = 0;
		Point next;
		switch(direction.getDirNum()){
		case DIR_UP:
			next = nextPos(pos, new Move(MoveType.UP));
			while(field.isClear(next.x, next.y)){
				dist++;
				Point temp = next;
				next = nextPos(temp, new Move(MoveType.UP));
			}
			break;
		case DIR_DOWN:
			next = nextPos(pos, new Move(MoveType.DOWN));
			while(field.isClear(next.x, next.y)){
				dist++;
				Point temp = next;
				next = nextPos(temp, new Move(MoveType.DOWN));
			}
			break;
		case DIR_LEFT:
			next = nextPos(pos, new Move(MoveType.LEFT));
			while(field.isClear(next.x, next.y)){
				dist++;
				Point temp = next;
				next = nextPos(temp, new Move(MoveType.LEFT));
			}
			break;
		case DIR_RIGHT:
			next = nextPos(pos, new Move(MoveType.RIGHT));
			while(field.isClear(next.x, next.y)){
				dist++;
				Point temp = next;
				next = nextPos(temp, new Move(MoveType.RIGHT));
			}
			break;
		}
		return dist;
	}

	//Get's next position of a pos in a given direction. Returns null if going off the board
	private Point nextPos(Point myPos, Move direction){
		Point res = null;
		try {
			switch(direction.getMoveType()){
			case UP:
				res = new Point(myPos.x, myPos.y-1);
				break;
			case DOWN:
				res = new Point(myPos.x, myPos.y+1);
				break;
			case LEFT:
				res = new Point(myPos.x-1, myPos.y);
				break;
			case RIGHT:
				res = new Point(myPos.x+1, myPos.y);
				break;
			case PASS:
				res = nextPos(myPos, lastMove);
				break;
			default:
				res = myPos;
				break;
			}
		} catch (Exception e) {
			res = null;
		}
		return res;
	}
	private int floodUse(Point p, Field field){
		ArrayList<Point> visited = new ArrayList<Point>();
		return floodFill(p.x, p.y, field, visited);
	}
	//Flood Fill beginning at x, y
	private int floodFill(int x, int y, Field field, ArrayList<Point> visited){
		//System.out.println(yo++);
		Point node = new Point(x, y);
		if(x<0 || y<0 || x>field.getWidth()-1 || y>field.getHeight()-1) return 0;//Out of bounds
		if(visited.contains(node)) return 0;//Hasn't already been visited
		if(!field.isClear(x, y)) return 0;//Doesn't equal "."

		visited.add(node);
		return 1 + 
				floodFill(x-1, y, field, visited) +
				floodFill(x+1, y, field, visited) +
				floodFill(x, y-1, field, visited) +
				floodFill(x, y+1, field, visited);

	}

	private boolean weAreAlone(List<Move> possibleMoves, Point myPos, Field field){
		int enemyCount = 0;
		for(Move m : possibleMoves){
			Point next = nextPos(myPos, m);
			ArrayList<Point> visited = new ArrayList<Point>();
			enemyCount += floodCount(next.x, next.y, field, visited);
		}

		//System.out.println("Enemy count: " + enemyCount);
		return enemyCount == 0;
	}
	//Flood Fill to search for enemies , beginning at x, y
	private int floodCount(int x, int y, Field field, ArrayList<Point> visited){
		Point node = new Point(x, y);
		if(x<0 || y<0 || x>field.getWidth()-1 || y>field.getHeight()-1) return 0;//Out of bounds
		if(visited.contains(node)) return 0;//Hasn't already been visited
		if(field.isClear(x, y)){
			visited.add(node);
			return 
					floodCount(x-1, y, field, visited) +
					floodCount(x+1, y, field, visited) +
					floodCount(x, y-1, field, visited) +
					floodCount(x, y+1, field, visited);//Doesn't equal "."
		}
		else{
			if(field.hasEnemy(x, y)){
				visited.add(node);
				return 1;
			}else{
				visited.add(node);
				return 0;
			}
		}
	}

	private Move voronoi(List<Move> possibleMoves, Point myPos, Point enemyPos, Field field){
		int[] voronoiValues = new int[possibleMoves.size()];
		//compare all possible moves
		for(int i = 0;i<possibleMoves.size()-1;i++){
			Move move = possibleMoves.get(i);
			
			//Create updated map
			String[][] map1 = field.getField();
			Point next = nextPos(myPos, move);
			map1[myPos.x][myPos.y] = "x";
			map1[next.x][next.y] = field.getMyId() + "";
			String[][] map2 = map1;
			
			int mySpaces = 0; //Spaces I can reach first
			int enemySpaces = 0; //Spaces enemy can reach first
			//For every point in field, check who's closer
			for(int y=0;y<field.getHeight();y++){
				for(int x=0;x<field.getWidth();x++){
					Point temp = new Point(x, y);
					leeMap(myPos, temp, map1, 0); //Map for my dist/point
					leeMap(enemyPos, temp, map2, 0); //Map for enemy dist/point
					if(field.isClear(x, y)){
						int myDist = leeDist(temp,map1);
						int enemyDist = leeDist(temp, map2);
						if(myDist < enemyDist) mySpaces++;
						else if(enemyDist < myDist) enemySpaces++;
					}
				}
			}
			voronoiValues[i] = mySpaces - enemySpaces;
		}

		//Find largest voronoi value and return corresponding move
		int maxIndex = 0;
		for (int i = 1; i < voronoiValues.length; i++) {

			if ((voronoiValues[i] > voronoiValues[maxIndex])) {
				maxIndex = i;
			}
		}
		return possibleMoves.get(maxIndex);
	}
	
	private int distInField(Point one, Point two, String[][] map){
		return Math.abs(one.x - two.x) + Math.abs(one.y - two.y);
	}
	
	private void leeMap(Point start, Point target, String[][] map, int dist){
		if(start.equals(target)) {
			map[start.x][start.y] = dist+""; 
			return;
		}
		if(map[start.x][start.y].equals(".")){
			map[start.x][start.y] = dist+"";
			leeMap(new Point(start.x-1, start.y), target, map, dist++);
			leeMap(new Point(start.x+1, start.y), target, map, dist++);
			leeMap(new Point(start.x, start.y-1), target, map, dist++);
			leeMap(new Point(start.x, start.y+1), target, map, dist++);
		}
		
	}
	private int leeDist(Point target, String[][] map){
		
		String res = map[target.x][target.y];
		//if(res.equals)
		return Integer.parseInt(res);
	}
	/**
	 * Main method for the bot. Creates a parser and runs it.
	 */
	public static void main(String[] args) {
		//Parser reads output from game engine into bot
		BotParser parser = new BotParser(new Bri_bot());
		parser.run();
	}
}
