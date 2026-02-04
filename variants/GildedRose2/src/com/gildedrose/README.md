The classical CoR: I don't like it because it mixes the update logic with the control logic. 
When separating we could use it within different control flows, e.g. first responsible updater get called, or last one or all of them or an exception is thrown if multiple updateres would like to update etc.
