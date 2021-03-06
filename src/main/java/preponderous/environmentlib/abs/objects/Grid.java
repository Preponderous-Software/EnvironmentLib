/*
  Copyright (c) 2022 Preponderous Software
  MIT License
 */
package preponderous.environmentlib.abs.objects;

import preponderous.environmentlib.misc.CONFIG;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author Daniel McCoy Stephenson
 * @since January 17th, 2022
 */
public abstract class Grid {
    private UUID uuid;
    private ArrayList<UUID> locationUUIDs = new ArrayList<>();
    private int columns;
    private int rows;
    private UUID parentEnvironmentUUID;

    public Grid(int columns, int rows, UUID parentEnvironmentUUID) {
        uuid = UUID.randomUUID();
        this.columns = columns;
        this.rows = rows;
        this.parentEnvironmentUUID = parentEnvironmentUUID;
    }

    public abstract Location getLocation(int x, int y);

    public UUID getUUID() {
        return uuid;
    }

    public ArrayList<UUID> getLocationUUIDs() {
        return locationUUIDs;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public UUID getParentEnvironmentUUID() {
        return parentEnvironmentUUID;
    }

    public UUID getFirstLocationUUID() {
        return locationUUIDs.get(0);
    }

    public void setLocationUUIDs(ArrayList<UUID> gridLocations) {
        this.locationUUIDs = gridLocations;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setParentEnvironmentUUID(UUID parentEnvironmentUUID) {
        this.parentEnvironmentUUID = parentEnvironmentUUID;
    }

    public void addLocationUUID(UUID uuid) {
        locationUUIDs.add(uuid);
    }

    public void removeLocationUUID(UUID uuid) {
        locationUUIDs.remove(uuid);
    }

    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                Location location;
                try {
                    location = getLocation(x, y);
                } catch (Exception e) {
                    toReturn.append("[N/A] ");
                    continue;
                }
                if (location.getEntityUUIDs().size() > 0) {
                    switch(CONFIG.DISPLAY_TYPE) {
                        case SIMPLE:
                            toReturn.append("[x] ");
                            break;
                        case CHARACTER_AT_INDEX_ZERO:
                            UUID entityUUID = location.getEntityUUIDs().iterator().next();
                            toReturn.append("[").append(entityUUID.toString().charAt(0)).append("] ");
                            break;
                        case NUMBER_OF_ENTITIES:
                            int numberOfEntities = location.getEntityUUIDs().size();
                            toReturn.append("[").append(numberOfEntities).append("] ");
                            break;
                    }

                }
                else {
                    toReturn.append("[ ] ");
                }
            }
            toReturn.append("\n");
        }
        return toReturn.toString();
    }

    protected void setUUID(UUID uuid) {
        this.uuid = uuid;
    }
}