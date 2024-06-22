# 2.0.0
- Rewrote major systems of the mod from scratch, primarily the Easy Registration system
- Removed support for Block Entities and Entities with this system as I don't like how it works and I haven't decided how to do it yet
- Support for custom registration types to be made by anyone who chooses to extend this library, though that's more to save me time and headaches adding new types
- Change sound registration to auto hookup into datagen, just like how ores do it now, so adding new sounds manually has been set to private to discourage it.

@TODOS
- Redo how sounds are generated to allows more customizability for any mod that extends the library
- Re-add support for Block Entities and Entities
- Add more support for NeptuneData for more things than just Items
- Possibly a system to help with animations???

# 1.9.0
- Update the mod to work with 1.21
- Changed some things around as `DataComponentType` is now `ComponentType`
- Removed some unfinished parts of the library as they haven't been touched and they broke from the update and I'm not bothered to fix it
- Changed versioning system for the mod to have the game version appended to the end

# 1.8.1
 - Changed EasyData to be used with the InitHandler