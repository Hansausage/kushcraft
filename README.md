# kushcraft
for minecraft 1.12.2, builds against jdk 8

# build

If you prefer to use Eclipse:
1. Run the following command: ```gradlew genEclipseRuns``` (```./gradlew genEclipseRuns``` if you are on Mac/Linux)
2. Open Eclipse, Import > Existing Gradle Project > Select Folder 
   or run ```gradlew eclipse``` to generate the project.
(Current Issue)
4. Open Project > Run/Debug Settings > Edit runClient and runServer > Environment
5. Edit ```MOD_CLASSES``` to show ```[modid]%%[Path];``` 2 times rather then the generated 4.

If you prefer to use IntelliJ:
1. Open IDEA, and import project.
2. Select your ```build.gradle``` file and have it import.
3. Run the following command: ```gradlew genIntellijRuns``` (```./gradlew genIntellijRuns``` if you are on Mac/Linux)
4. Refresh the Gradle Project in IDEA if required.

If at any point you are missing libraries in your IDE, or you've run into problems you can run "gradlew --refresh-dependencies" to refresh the local cache. "gradlew clean" to reset everything {this does not affect your code} and then start the processs again.

Either run from debugging in ide or ```gradlew runClient``` for testing client, and ```gradlew runServer``` for server.
