package org.sb.engine.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.sb.mdl.enm.TileEnum;
import org.sb.mdl.cnst.GameConstants;

public class TileManager {
	private final static Random rnd = new Random(); 
	private final static int TILE_INDEX_MAX = TileEnum.RED.getIndex();
	private Map<TileEnum,Integer> tileHistory = new HashMap<TileEnum,Integer>();



	
	public TileEnum takeTileFromTable(){
		TileEnum tile = null;
		while(tile == null){
			int index = rnd.nextInt(TILE_INDEX_MAX + 1);
			tile = TileEnum.getEnum(index);
			if(tile == null){
				continue;
			}

			Integer num = tileHistory.get(tile);

			if(num == null){
				tileHistory.put(tile,1);				
			}else if(num >= GameConstants.EACH_TILE_MAX){
				tile = null;
				continue;
			}else{
				tileHistory.put(tile,num + 1);				
			}
		
		}

		int sum=0;
		for(int k : tileHistory.values()){
			sum += k;
		}
//		System.out.println(":" + sum + "::" + tileHistory.size() + ":" +  tileHistory.values());		
		return tile;
	}
	public List<TileEnum> createInitialTiles(){
		List<TileEnum> tiles = new ArrayList<TileEnum>();
		int i = 0;
		while( i < GameConstants.MAX_WALL_TILE_NUM - 1){

			tiles.add(takeTileFromTable());
			i++;
		}
		return tiles;
	}
	
	public TileEnum takeDora(){
		TileEnum displayedDoraTile = takeTileFromTable();
		TileEnum doraTile = displayedDoraTile.toDoraTile();
		return doraTile;
		
	}
}
