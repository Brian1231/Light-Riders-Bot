// // Copyright 2016 riddles.io (developers@riddles.io)

//    Licensed under the Apache License, Version 2.0 (the "License");
//    you may not use this file except in compliance with the License.
//    You may obtain a copy of the License at

//        http://www.apache.org/licenses/LICENSE-2.0

//    Unless required by applicable law or agreed to in writing, software
//    distributed under the License is distributed on an "AS IS" BASIS,
//    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//    See the License for the specific language governing permissions and
//    limitations under the License.
//  
//    For the full copyright and license information, please view the LICENSE
//    file that was distributed with this source code.

package field;

import java.awt.Point;

/**
 * field.Field
 *
 * Handles everything that has to do with the field, such 
 * as storing the current state and performing calculations
 * on the field.
 *
 * @author Jim van Eeden <jim@riddles.io>
 */

public class Field {

	private int myId;
	private int enemyId;
	private int width;
	private int height;
	private String[][] field;
	private Point myPosition;
	private Point enemyPosition;

	/**
	 * Initializes and clears field
	 * @throws Exception exception
	 */
	public void initField() throws Exception {
		try {
			this.field = new String[this.width][this.height];
		} catch (Exception e) {
			throw new Exception("Error: trying to initialize field while field "
					+ "settings have not been parsed yet.");
		}

		clearField();
	}

	/**
	 * Clears the field
	 */
	private void clearField() {
		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width; x++) {
				this.field[x][y] = ".";
			}
		}

		this.myPosition = null;
	}

	/**
	 * Parse field from comma separated String
	 * @param s input from engine
	 */
	public void parseFromString(String s) {
		clearField();

		String[] split = s.split(",");
		int x = 0;
		int y = 0;

		for (String value : split) {
			this.field[x][y] = value;

			if (this.field[x][y].equals(this.myId + "")) {
				this.myPosition = new Point(x, y);
			}
			if (this.field[x][y].equals(this.enemyId + "")){
				this.enemyPosition = new Point(x, y);
			}

			if (++x == this.width) {
				x = 0;
				y++;
			}
		}
	}

	//Check if space is clear
	public boolean isClear(int x, int y){

		if(x < 0 || x > this.width-1 || y < 0 || y > this.height-1) return false;

		return this.field[x][y].equals(".");

	}

	//Check if space has 4 walls 
	public boolean isDeadEnd(int x, int y){
		int wallCount = 0;
		//Check neighbours of given pos
		if(!isClear(x, y+1)) wallCount++;
		if(!isClear(x, y-1)) wallCount++;
		if(!isClear(x+1, y)) wallCount++;
		if(!isClear(x-1, y)) wallCount++;

		return wallCount >= 4;

	}

	public void setIds(int me, int enemy) {
		this.myId = me;
		this.enemyId = enemy;	
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Point getMyPosition() {
		return this.myPosition;
	}
	
	public Point getEnemyPosition() {
		return this.enemyPosition;
	}

	public int getHeight(){
		return this.height;
	}

	public int getWidth(){
		return this.width;
	}
	public void blockPoint(Point p){
		if(p.x < 0 || p.x > this.width-1 || p.y < 0 || p.y > this.height-1) return;
		field[p.x][p.y] = "x";
	}
	public boolean hasEnemy(int x,int y){
		if(x < 0 || x > this.width-1 || y < 0 || y > this.height-1) return false;
		
		return !field[x][y].equals(".") && !field[x][y].equals("x") && !field[x][y].equals(this.myId + "");
	}
	public String toString(){
		String res = "";
		for(int y=0;y<this.height;y++){
			for(int x=0;x<this.width;x++){
				if(field[x][y] != null){
					res += field[x][y];
				}
			}
			res += "\n";
		}
		return res;
	}
	public String[][] getField(){
		return this.field;
	}
	public int getMyId(){
		return this.myId;
	}
}