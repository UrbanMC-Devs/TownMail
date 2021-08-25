# TownMail
An unofficial Towny add-on that adds a sub-command, `/town mailall [message]` which allows
players to send an Essentials mail to all residents within their town.

In order to use the command, the player must have the permission `towny.command.town.mailall`.
It is recommended to give this permission node to mayors or co-mayors specified within
the towny perms file.

## Dependencies
This plugin requires the following plugins/software to be installed in order to function:
* [Essentials](https://www.spigotmc.org/resources/essentialsx.9089/) 2.18 (or greater)
* [Towny](https://www.spigotmc.org/resources/towny-advanced.72694/) 0.97.1 (or greater)
* Java 16 (or greater)

## Installing
Download this plugin from the [releases page](https://github.com/UrbanMC-Devs/TownMail/releases/latest) and add it to your plugins directory.

## Building
This plugin is a simple maven project. Before building make sure to have maven, git, and java
installed on your machine. Then in the project directory, execute `mvn clean package` and the
jar will be produced in the `target` folder.

### Developed by Silverwolg11 & Elian