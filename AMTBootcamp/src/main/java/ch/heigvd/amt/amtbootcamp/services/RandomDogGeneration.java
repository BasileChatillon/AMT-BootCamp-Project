
package ch.heigvd.amt.amtbootcamp.services;

import ch.heigvd.amt.amtbootcamp.model.Dog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;

@Stateless
public class RandomDogGeneration implements RandomDogGenerationLocal {

    // Pour faire les choses hyper bien, on aurait du mettre les noms et les quotes dans une base de donnée ou au dans un fichier de ressource.
    private final ArrayList<String> dogNames = new ArrayList<>(Arrays.asList("Butch", "Fang", "Rufus", "Alpha", "Brave", "Glory", "Holorkar", "Feronar", "Keilin", "Geinar", "Korotar", "Nikar", "Loruss", "Sakura", "Omai", "Tao", "Caleb", "Conor", "Wuffles", "Scraps", "Scruffy", "Trixibell", "Dribbles", "Foofy", "Spike", "Butch", "Fido", "Hooch", "Bounty", "Rover", "Chicken", "Buddy", "Scruffy", "Srappy", "Fleabag", "Cookie", "Lucky", "Toad", "Abby", "Abel", "Abercrombie", "Abracadabra", "Ace", "Achilles", "Action", "Admiral", "Adonis", "Adriatic", "Aesop", "Affinity", "Ajax", "Akira", "Aladdin", "Alastair", "Alcatraz", "Alchemy", "Aldo", "Alexia", "Alfalfa", "Alger", "AliBaba", "Allegro", "Aloysius", "Alpaca", "Alpha", "Alpine", "Amable", "Amaretto", "Amazon", "Ambassador", "Amber", "Ambrosia", "Amethyst", "Amigo", "Ammo", "Amore", "Amos", "Ampersand", "Anaconda", "Anais", "Anastasia", "Andalusia", "Andorra", "Android", "Andromeda", "Angel", "Angstrom", "Angus", "Animal", "Apache", "Apogee", "Apollo", "Apprentice", "April", "Aquarius", "Arapaho", "Archie", "Ares", "Argus", "Ari", "Ariel", "Aries", "Aristotle", "Armani", "Armstrong", "Artemis", "Arty", "Ashes", "Aspen", "Asphalt", "Asset", "Astro", "Asylum", "Athena", "Atlantis", "Atlas", "Atom", "Atticus", "Attila", "Audacity", "Auditor", "August", "Augustus", "Aureole", "Aurora", "Aussie", "Austin", "Autobahn", "Autumn", "Avalanche", "Avalon", "Avanti", "Avis", "Axle", "BB", "Baba", "Babbit", "Babe", "Babette", "Babirusa", "Babs", "Babushka", "Baby", "BabyDoll", "BabyFace", "Babykins", "Baccarat", "Bacchus", "Bach", "Baci", "Bacon", "Badger", "Baguette", "BahamaMama", "Bailey", "Baja", "Balboa", "Baldric", "Baldwin", "Baldy", "Bali", "Ballerina", "BamBam", "Bambi", "Bambino", "Bamboozler", "Bandanna", "Bandit", "Bandito", "Bangle", "Banjo", "Banner", "Banquo", "Banshee", "Banzai", "Barbarian", "Barbarino", "Barfly", "Barnacle", "Barney", "Baron", "Baroque", "Bart", "Bartholomew", "Barton", "Basel", "Bashful", "Basil", "Bass", "Baxter", "Bayou", "Bazooka", "Beachbaby", "Beachcomber", "Beamer", "Beans", "Bear", "Beau", "Beaucoup", "Beauregard", "Beauty", "Bedouin", "Beefeater", "Beelzebub", "Beeper", "Beethoven", "Beetlejuice", "Begonia", "BellaMia", "Belladonna", "Bellboy", "Belle", "Beluga", "Ben", "Bengal", "Benji", "Bennington", "Bentley", "Beowoof", "Berber", "Beret", "Bertha", "Bessie", "Beta", "BettyBoop", "Beulah", "Bewitched", "Bialy", "Bianca", "Bianco", "Biddy", "Bridget", "BigBoy", "BigDipper", "BigTime", "Bigfoot", "Bijou", "Biker", "Biko", "Bimbo", "Bina", "Bingo", "Birdy", "Bishop", "Bismarck", "Bits", "Bizzy", "Blackbird", "Blackie", "Blackjack", "Blanche", "Blaze", "Blimp", "Blitzen", "Blondie", "Blossom", "Blotto", "Blubber", "Blue", "Blueberry", "Blueprint", "Blues", "Blunder", "Boa", "Bob", "Bobo", "Boca", "Boccaccio", "Bodacious", "Bogart", "Bogeyman", "Bogie", "Bohemian", "Bojangles", "Bolero", "BollWeevil", "Bolla", "Bologna", "Bombshell", "Bonbon", "Bond", "Bones", "Bonfire", "Bongo", "Bono", "Bonsai", "Boober", "Boo", "Booboo", "Boogaboo", "BoogieWoogie", "Booker", "Bookie", "BoomBoom", "Boomer", "Boone", "Boots", "Boozer", "Bora", "Boris", "Borscht", "Boss", "Bossman", "Boubie", "Bouba", "Bouffant", "Bouncer", "Bouquet", "Boxer", "Boy", "Boz", "Bozo", "Bradstreet", "Brain", "Brainchild", "Braindead", "Brando", "Brandy", "Brandywine", "Brassy", "Brat", "Bratwurst", "Brautty", "Bravo", "Breathless", "Mahoney", "Brew", "Brewsky", "Brie", "Brier", "Brit", "Brittany", "Broadway", "Bronco", "Bronson", "Bronte", "Brouhaha", "Brownie", "Bruin", "Brunhilda", "Bruno", "Brutus", "Bubba", "Bubbles", "Buccaneer", "Buck", "Buckaroo", "Buckeye", "Buckhead", "Buckingham", "Bucko", "Buckshot", "Buckwheat", "Bud", "Buddy", "Buffoon", "Buffy", "Bug", "Buginarug", "Bugsy", "Bugtussie", "Buick", "Bullet", "Bullface", "Bullion", "Bullwinkle", "Bum", "Bumper", "Bumpkin", "Bunk", "Bunny", "Burger", "Burgundy", "Burly", "Burma", "Burp", "Buster", "Butch", "Butkus", "Butter", "Butterball", "Buttercup", "Buttermilk", "Butterscotch", "Butts", "Buzz", "Buzzard", "Byron", "Byte", "Cabaret", "Cabo", "Caboodle", "Caboose", "Cadbury", "Caddy", "Cadence", "Cadet", "Cadillac", "Caesar", "Cagney", "Cairo", "Cajun", "Calamity", "Caliber", "Calico", "California", "Calloway", "Calvin", "Calypso", "Camelot", "Camembert", "Cameo", "Camus", "Candy", "Canis", "Caper", "Capo", "Capone", "Capricorn", "Captain", "Caramba", "Caramel", "Caramelo", "Cargo", "Caribou", "Carnation", "Carob", "Carolina", "Carraway", "Casanova", "Casbah", "Casey", "Cash", "Cashmere", "Casino", "Caspar", "Casper", "Caspian", "Cassandra", "Cassidy", "Cassie", "Cassis", "Cassius", "Castaway", "Catalina", "Catamaran", "Caviar", "Cayenne", "Cecil", "Cecily", "Cerberus", "Chablis", "ChaCha", "Chalet", "Challenger", "Chambray", "Chamois", "Champagne", "Champion", "Chanel", "Chantilly", "Chaos", "Chaplin", "Charade", "Charlotte", "Charmer", "Chaucer", "Chauffeur", "Chaya", "Checca", "Check", "Checkers", "Cheddar", "Cheerleader", "Cheetah", "Chekhov", "Chelsea", "Cherub", "Chester", "Chewbacca", "ChewChew", "Chewy", "Cheyenne", "Chianti", "Chic", "Chiclet", "Chico", "Chicory", "Chief", "Chiffon", "Chili", "China", "Chinchilla", "ChinChin", "Chip", "Chipmunk", "Chipper", "Chips", "Chiquita", "Chivas", "Chloe", "ChoMein", "Chocolata", "Chocolate", "Choctaw", "Cholo", "ChooChoo", "Chopin", "Chopper", "Chopsticks", "Chorizo", "Chotchke", "Chowder", "Chubby", "Chubfish", "Chump", "Chunk", "Chunky", "Churchill", "Chutney", "Chutzpa", "Ciao", "Cimarron", "Cinder", "Cinnamon", "Circe", "Cisco", "Citron", "Citrus", "Classy", "Clementine", "Cleo", "Cliche", "Clipper", "Clone", "Clover", "Clyde", "Coconut", "Cocopuff", "Cody", "Cognac", "Cola", "Colonel", "Colt", "Comet", "Compadre", "Conan", "Condor", "Confection", "Confetti", "Confucius", "Conga", "Congo", "Conjurer", "Cookie", "CookieMonster", "Coon", "Cooter", "Copper", "Coquette", "Coral", "Corazon", "Cordial", "Cordoba", "Cori", "Corky", "Corona", "Coronet", "Cory", "Cosmo", "CouchPotato", "Count", "Courvoisier", "Couscous", "Cousteau", "Cowboy", "Cowgirl", "Cracker", "Cranberry", "Crash", "Crawdad", "Crawford", "Crescent", "Cricket", "Crier", "Crimson", "Cristyl", "Critter", "Crockett", "Crocus", "Croissant", "Cromwell", "Crouton", "Cruiser", "Crumb", "Crumpet", "Cruncher", "Crusader", "Crusoe", "Crybaby", "Crystal", "Cubby", "Cucaracha", "Cucina", "Cuckoo", "Cuddles", "Cuervo", "Cupcake", "CuriousGeorge", "Curly", "Curmudgeon", "Curry", "Cutie", "CutiePie", "Cypress", "Cyrano", "Czar", "DaVinci", "Dagwood", "Daiquiri", "Daisy", "Dakota", "Dali", "Dallas", "Damage", "Damsel", "Dancer", "Dandelion", "Dank", "Dante", "Danu", "Danube", "Daphne", "Daredevil", "Dash", "Dasher", "Dawn", "Dazzler", "Debutante", "Deeogee", "Degas", "DejaVu", "Delilah", "Delta", "Derby", "Desdemona", "Desiree", "Desoto", "Desperado", "Detonator", "Detour", "Deuce", "Devil", "Dew", "Dexedrine", "Diablo", "Diamond", "Dice", "Dickens", "Diddley", "Didi", "Digby", "Digger", "DimSum", "Dingo", "Dino", "Diva", "Divot", "Dixie", "Dizzy", "Doc", "Dodger", "Dodo", "Doglet", "Dogzilla", "Doheny", "Dolce", "Dolly", "Domino", "Don", "Donner", "Doodle", "Doogie", "Doolittle", "Doonesbury", "Dots", "DoubleOseven", "DoughBoy", "Dozer", "DrWho", "Draco", "Dragon", "Drambuie", "Dreadnought", "Dreamer", "Dreamweaver", "Droopy", "Drummer", "Dubonnet", "Duchess", "Dude", "Dudette", "Dudley", "Duffy", "Dugong", "Duke", "Dulce", "Dumpling", "Dun", "Duncan", "Dunk", "Durango", "Dustmop", "Dusty", "Dweeb", "Dylan", "Dynamite", "Dynamo", "Ebi", "Ebony", "Echo", "Eclipse", "Edelweiss", "Edge", "Eggo", "Einstein", "Elf", "Elias", "Elmer", "Elmo", "Elroy", "Elsa", "Elvira", "Elvis", "Emerald", "Emerson", "Emily", "Emma", "Enchilada", "Energizer", "Enzo", "Epcot", "Epic", "Epstein", "Equity", "Errol", "Escapade", "Escort", "Eskimo", "Esprit", "Espresso", "Euphrates", "Eureka", "Europa", "Ewok", "Excalibur", "Exeter", "Fable", "Face", "Fajita", "Falafel", "Falcon", "Fancy", "Fandango", "Fang", "Fantasia", "Fats", "Fatso", "Fatty", "Fauna", "Faust", "FauxPas", "Fedora", "Felix", "Fender", "Fennel", "Fenway", "Ferdinand", "Ferreo", "Fido", "Fiesta", "Fifi", "Figaro", "Fiji", "Fikri", "Fink", "Finn", "Firebug", "Firecracker", "Fishbone", "Fizz", "Flannel", "Flapper", "Flash", "Fletch", "Fleur", "Flicker", "Fling", "Flipper", "Flirt", "Flora", "Flower", "Floyd", "Fluffy", "Flurry", "Flush", "Fondue", "Fonzi", "Football", "Formosa", "Foxy", "Frankenstein", "Frankie", "Frazier", "Freckles", "Freebie", "Freeway", "Freud", "FriarTuck", "Friday", "Frisbee", "Frisky", "Fritz", "Froggy", "Frosty", "Fuchsia", "Fudd", "Fuddles", "Fudge", "Fungus", "Funky", "FunnyGirl", "Furball", "Furface", "Furioso", "Furr", "Fuzzie", "Gaelic", "Galileo", "Gambler", "Garbo", "Gargoyle", "Garlic", "Garp", "Gatekeeper", "Gator", "Gatsby", "Gavin", "Gazelle", "Gazpacho", "Gecko", "Geekie", "Geezer", "Geisha", "Gemini", "Genesis", "Genghis", "Genie", "Genius", "Georgia", "Geppetto", "Gerbil", "Geronimo", "Gertrude", "Ghiradelli", "GhostBuster", "Giddy", "Gideon", "Gidget", "Giggles", "Gigi", "Gigolo", "Gilligan", "Gimp", "Gin", "Ginger", "Gingersnap", "Gingham", "Ginseng", "Gipper", "Girl", "Girlfriend", "Giro", "Giselle", "Gizmo", "Gladstone", "Gnome", "Gobble", "Goblin", "Godfather", "Godzilla", "Goethe", "Goldie", "Goldilocks", "Goliath", "Gomer", "Goober", "Gooch", "Goomba", "Goose", "Gopher", "Gordo", "Gorgeous", "Gorilla", "Gorky", "Gouda", "Grace", "Grandeur", "Grazie", "Gremlin", "Gretel", "Greystoke", "Grits", "Grizzly", "Groucho", "Grover", "Grumpus", "Grumpy", "Grunge", "Grunion", "Grunt", "Guaymas", "Gucci", "Guffaw", "Guido", "Guinevere", "Gulliver", "Gumball", "Gumbo", "Gumby", "Gumdrop", "GummieBear", "Gumps", "GungHo", "Gunner", "Gunther", "Guru", "Gus", "Gussy", "Gusto", "Guy", "Gypsy", "Gyro", "Hachiko", "HackiSack", "Halcyon", "Haley", "Ham", "Hamlet", "Hammer", "Hancock", "Handsome", "Hannibal", "Hansel", "Happy", "HappyFeet", "HardRock", "Hardy", "Harley", "Harmony", "Harpo", "Harvey", "Haskell", "Havana", "Havoc", "Hawk", "Hawkeye", "Hazer", "Heartbreaker", "Hearts", "Heathcliff", "Heather", "Heaven", "Hedgehog", "Hefty", "Heidi", "Heiress", "Hercules", "Herman", "Hermes", "Hermit", "Hero", "Hershey", "Hickory", "Hilda", "Hippo", "Hippodrome", "Historia", "Hoagie", "Hobbit", "Hobo", "HocusPocus", "Hog", "Holly", "Hollywood", "Homeboy", "Homer", "Homey", "Honcho", "Honey", "HoneyBear", "Honeybee", "HonkyTonk", "Hooch", "Hood", "Hoofman", "Hook", "Hooligan", "Hoosier", "Hoover", "Hope", "Horace", "Horatio", "Horshack", "Horus", "HotDog", "Hotshot", "Houdini", "Howard", "Howler", "HubbaHubba", "Hubble", "Huck", "Hudson", "HuggerMugger", "Hugo", "Hulk", "Humdinger", "Humphrey", "Hunter", "Huntress", "Hurricane", "Hyde", "Icarus", "Iceman", "IceT", "Icon", "Ida", "Iditarod", "Iggy", "Igloo", "Ignatius", "Igor", "Inca", "Indie", "Indigo", "Infinity", "Inky", "Intelectus", "Intrepid", "Iodine", "Iota", "Ira", "Irie", "Iris", "Irresistible", "Isabelle", "Ishta", "Ishtar", "Isis", "Ivy", "Jabberwocky", "JackDaniels", "Jackpot", "Jade", "Jag", "Jailbait", "Jake", "Jalapeno", "Jamaica", "Jasmine", "Jasper", "Java", "Jazz", "Jed", "Jedi", "Jekyll", "JellyBean", "JellyBelly", "Jemima", "Jerky", "Jersey", "Jesse", "Jet", "Jethro", "Jezebel", "Jigsaw", "Jingles", "Jinx", "Jitterbug", "Joker", "Joy", "Jubilee", "Judge", "Jughead", "Julep", "Juliet", "Jumbo", "June", "Junior", "Juniper", "Jupiter", "Justus", "KO", "K9", "Kafka", "Kahlua", "Kaiser", "Kalamazoo", "Kaleidoscope", "Kandinsky", "Karma", "Katmandu", "Kayle", "Keepers", "Kellogg", "Keno", "Kentucky", "Kenya", "Kermit", "Kewpie", "Kibbles", "Kid", "Kielbasa", "Kiki", "Kiku", "Killer", "Kilo", "Kimbundu", "King", "Kinko", "Kipling", "Kisses", "Kiwi", "Klondike", "Klutz", "Knaidel", "Knickerbocker", "Knockout", "Knuckles", "Koala", "Kobi", "Koda", "Koko", "Kona", "Kookaburra", "Kool", "Korbel", "Kosmic", "Kosmo", "Krispy", "Kryptonite", "Kudu", "Kujo", "Kuma", "Labyrinth", "Lacey", "Ladron", "Lady", "Laker", "Lambchop", "Lamborghini", "Lancelot", "Laser", "Lashes", "Lassie", "Lavender", "Lazarus", "Leila", "Lemon", "Leo", "Levi", "Lexis", "Liability", "Liberty", "Lickums", "Liebchen", "Lightning", "Lilac", "Lilith", "Limbo", "Link", "Linus", "Lion", "Lionheart", "Lips", "Lira", "Lisbon", "Litmus", "LittleBit", "LittleGuy", "LittleOne", "Lizard", "Lobo", "Lola", "Lolita", "Lolly", "Loony", "Lotus", "Lover", "Lovesong", "Lovey", "LoveyDovey", "Lucky", "Ludwig", "Lullaby", "Lulu", "Lunatic", "Mac", "MacAttack", "Macbeth", "Machete", "Machiavelli", "Macho", "Macintosh", "Mackerel", "Macmillan", "MadMax", "Maduro", "Maestro", "Mafioso", "Maggie", "Magic", "Magna", "Magnet", "Magnolia", "Magnum", "Magpie", "Mahimahi", "Mahogany", "MaiTai", "Mailman", "Majestic", "Major", "Mako", "Malcolm", "Mamacita", "Mamba", "Mambo", "Mamie", "Manchu", "Mandy", "Manhattan", "Manners", "MapleSyrup", "Maraschino", "Marauder", "Marble", "MardiGras", "Mariachi", "Marigold", "Marilyn", "Marlboro", "Marley", "Marmaduke", "Marmalade", "Marquee", "Marshmallow", "Martian", "Martini", "Matador", "Matilda", "Matisse", "Matok", "Mattie", "Matzo", "Maui", "Maverick", "Max", "May", "Maya", "Mayhem", "Maynard", "MazelTov", "Mccoy", "MeToo", "Meadow", "Meatloaf", "Medea", "Medusa", "MeeKrob", "Melange", "Mellow", "Melody", "MelonHead", "Melvin", "Menage", "Mercedes", "Merlin", "Mesquite", "Metis", "Metro", "Miata", "Mickey", "Midas", "Midget", "Midnight", "Mignon", "Miles", "Minerva", "Ming", "Mink", "Minnie", "Miro", "Misery", "Misha", "Missie", "MissingLink", "Mistletoe", "Mocha", "Moet", "Mogul", "Mohave", "Mohawk", "Mohican", "MoJo", "Mole", "Mona", "MonaLisa", "Mongoose", "Monk", "Monkey", "Montego", "Monty", "Moochy", "Moody", "Moonbeam", "Moondance", "Moongoddess", "Moonmist", "Moonraker", "Moonshine", "Moonstruck", "Moose", "Mopsy", "Moptop", "Mork", "Morocco", "Morris", "Mortimer", "Moselle", "Moustache", "Mouton", "Movado", "Moxie", "Mozart", "MuShu", "Mudd", "Muddles", "Mudpie", "Muenster", "Muffin", "Mug", "Mugsy", "Mulberry", "Mumbles", "Muncher", "Munchkin", "Munchykin", "Muse", "Mustang", "Mutant", "Mutt", "Mystic", "Myth", "Nacho", "Nairobi", "Napa", "Napoleon", "Nappy", "Navajo", "Navar", "Navigator", "Nellie", "Neon", "Neptune", "Neutron", "Newsprint", "Newt", "Newton", "Nibbles", "Nickleby", "Nickles", "Nietzsche", "Nifty", "Nightmare", "Nike", "Nikita", "Niko", "Nina", "Ninja", "Nipper", "Nissan", "Nistka", "Nitro", "Noah", "Noel", "Noisemaker", "Noisette", "Nomad", "Nosferatu", "Nova", "Novella", "Nudge", "Nudnik", "Nugget", "Nutmeg", "Nymph", "Oberon", "Odyssey", "Oink", "Okie", "Okra", "Olsport", "OldYeller", "Oliver", "Ollie", "Omega", "Onomatopoeia", "Onyx", "OogaBooga", "Opal", "Ophelia", "Orbit", "Oreo", "Orion", "OrphanAnnie", "Oscar", "Othello", "Otis", "Otto", "Ouija", "Outlaw", "Outrigger", "Ouzo", "Ox", "Oz", "Ozone", "Ozzie", "Pachyderm", "Paco", "Paddington", "Paddy", "Padua", "Pagan", "Pagoda", "Paisley", "Pal", "Paloma", "Palomino", "Panache", "Panama", "Panda", "Pandemonium", "Pandora", "Pansy", "Panther", "Papillon", "Papoose", "Paprika", "Papu", "Papyrus", "Paradise", "Paragon", "Paris", "Patches", "Pate", "Pathfinder", "Patriot", "Patsy", "Patton", "Pauper", "Pavlov", "Paws", "Pazzo", "Peabody", "Peaches", "Peanuts", "Peaseblossom", "Pebbles", "Peewee", "Penelope", "Penn", "Penne", "Pepe", "Pepper", "Peppermint", "Peritar", "Periwinkle", "Pernod", "Perro", "Persephone", "Perseus", "Peso", "Pest", "PetitAnge", "Petra", "Petrarch", "Petulia", "Petunia", "Peugeot", "Peyote", "Phantom", "Pharaoh", "Pharos", "Phoenix", "Piazza", "Picasso", "Piccolo", "Pickles", "Pickwick", "Piedmont", "PigglesWiggles", "Piggy", "PiggyWiggy", "Ping", "Pinky", "Pinocchio", "Pinto", "Piper", "Pippi", "Piranha", "Pirate", "Pirouette", "Pisces", "Pistol", "Piston", "PitStop", "PitterPatter", "Pizzazz", "Plato", "Platty", "Platypus", "Playgirl", "Pluto", "Poco", "Poet", "Pogo", "Poindexter", "Pokerface", "Politix", "Polly", "Polo", "Poltergeist", "PomPom", "Pong", "Pongo", "Pooch", "Pooh", "PoohBah", "Pookie", "Pop", "Popeye", "Porkchop", "Porker", "Porsche", "Portia", "Portly", "Potpourri", "PouillyFuisse", "Prancer", "Precious", "President", "Presto", "Prettipaws", "Pretty", "PrettyBaby", "Pretzel", "PrimaDonna", "Primo", "Prissy", "Prodigy", "Professor", "Proton", "Prudence", "Puck", "Pudding", "Puddles", "Pudge", "Pudgette", "Puff", "Puffball", "Pugnose", "Pumpkin", "Punch", "Punk", "Pupa", "Puppy", "PuttPutt", "Pygmy", "Qantas", "Quasi", "Quasimodo", "QueenBee", "Queeny", "Quiche", "Quick", "Quicksilver", "Quincy", "Quirk", "Quixote", "Radar", "Ragdoll", "Rage", "RaggedyAnn", "RagingBull", "Ragmop", "Rags", "Ragtime", "Ragu", "Raider", "Rain", "Rainbow", "Raisin", "Rajah", "Ralph", "Rambo", "Ranger", "Rascal", "Rasselas", "Rasta", "Ravel", "Raven", "Rawhide", "Ray", "Razz", "Razzmatazz", "Rebel", "Red", "Redbeard", "Redhead", "Reebok", "Reef", "Regalo", "Rem", "Remington", "Remy", "Renegade", "Reno", "Repo", "Reuben", "Rex", "Rhapsody", "Rhea", "Rhett", "Rhinestone", "Rhino", "Rhodes", "Rhone", "Riches", "RickyTickyTavi", "Ricochet", "Ricotta", "Rider", "Riffraff", "RinTinTin", "Ringleader", "Ringo", "Rio", "Ripley", "Ripple", "Ritz", "Ritzy", "Roach", "RobRoy", "Rocca", "Rock", "Rocker", "Rocket", "Rocko", "Rocky", "Rococo", "Rodeo", "Rogan", "Rogue", "Rojo", "Rolls", "Roma", "Romeo", "Romulus", "Rondeau", "Rookie", "Rooster", "RootBeer", "Rosarita", "Roscoe", "Rosebud", "Rosie", "Rot", "Rothschild", "Rotten", "Rover", "Rowdy", "Roxanne", "Roxy", "Royal", "Ruby", "Ruff", "Ruffian", "Ruffles", "Rufus", "Rugrat", "Runner", "Runt", "Rupert", "Rusty", "Sabbath", "Saber", "SaberTooth", "Sable", "Sadie", "Safari", "Saffron", "Saga", "Sage", "Sahara", "Sailor", "Sake", "Salamander", "Salsa", "Salty", "Salvatore", "Sam", "Sambuca", "SamIam", "Sammy", "Samson", "Samurai", "Sandman", "Sangria", "SantaFe", "Santini", "Santo", "Sapphire", "Sarasota", "Sarge", "Sasha", "Sashimi", "Sasquatch", "Sassafras", "Sassy", "Satay", "Satchmo", "Satin", "Sauce", "Saucy", "Sausage", "Savage", "Savannah", "Savernake", "Savoy", "Scamper", "Scampi", "Scarborough", "Scarlet", "Schatze", "Schmoo", "Schmooze", "Schnapps", "Schnook", "Schooner", "Scintilla", "Scoobie", "Scooter", "Scorpio", "Scotch", "Scoundrel", "Scrappy", "ScrappyToo", "Scrooge", "Scruffy", "Scrumptious", "Scuba", "Scud", "Scylla", "SeaDog", "Seabreeze", "Seal", "Seneca", "Sera", "Serenade", "Serendipity", "Serengeti", "Sergeant", "Seuss", "Seychelles", "Seymour", "Shadow", "Shah", "Shakespeare", "Shalimar", "Shamrock", "Shamu", "Shane", "ShangriLa", "Sharkey", "Sharp", "Shawnee", "Sheba", "SheDevil", "Sheer", "Sheik", "Shelley", "Shenanigans", "Sheriff", "Sherlock", "Sherwood", "Shiitake", "Shiksa", "ShimShimShiree", "Shiva", "Shoeless", "Shogun", "Shooter", "Shorty", "Shortcake", "Shortstack", "Shotgun", "Showboat", "Showoff", "Shramsberg", "Shrapnel", "Shredder", "Shrimp", "Shylock", "Siciliano", "Sideways", "Sidney", "Siegfried", "Sienna", "Sierra", "Sigmund", "Silhouette", "Silky", "Silver", "Silverspoon", "SimplyRed", "Sin", "Sinatra", "Sinbad", "Sinclair", "Singer", "Sissy", "SittingBull", "SixToes", "Skedaddle", "Skeeder", "Skidder", "Skipper", "Skippy", "Skittles", "Sky", "SkyHawk", "Skyrocket", "Skywalker", "Slate", "Sleestack", "Slicker", "Slippers", "SlobberPuss", "Sloth", "Slugger", "Sly", "Smasher", "Smokey", "Smoocher", "Smores", "Snaggletooth", "Snaker", "Snap", "Snarl", "Sneakers", "Sneezy", "Snickers", "Sniffer", "Sniffles", "Snifter", "Snocap", "Snoogybear", "Snooker", "Snooks", "Snookums", "Snooper", "Snoopy", "Snooty", "SnoPea", "Snorter", "Snow", "Snowball", "Snowberry", "Snowbunny", "Snowflake", "Snowman", "Snuffles", "Snuggles", "SnugglePuss", "Socks", "Socrates", "Soleil", "Solo", "Solomon", "Solstice", "Sonata", "Sooner", "Sophie", "Sorbet", "Sorcerer", "Sorceress", "Soueee", "Sourdough", "Southpaw", "Spades", "Spaghetti", "Spago", "Spam", "Spanky", "Sparkle", "Sparkey", "Sparkplug", "Sparrow", "Specs", "Speedy", "Spellbinder", "Sphinx", "Spicy", "Spider", "Spike", "Spinner", "Spirit", "Spitfire", "Splash", "Spock", "Spooky", "Sport", "Spot", "Sprite", "Spuds", "Spumanti", "Spunky", "Sputnik", "Spy", "Spyro", "Squat", "Squeaky", "Squid", "Squirt", "Star", "Stardust", "Stargazer", "Starlet", "Starlight", "Stella", "Stellar", "Sterling", "Stetson", "Sting", "Stinker", "StinkerBelle", "Stinky", "Stoli", "Stoney", "Storm", "Stormy", "Stowaway", "Stray", "Strega", "Strudel", "Stubbles", "Stubby", "Stump", "Sugar", "Sugarbaby", "Sugarbaker", "SugarPlum", "Sukiyaki", "Sulfur", "Sultan", "Sumo", "Sundance", "Sundown", "Suni", "Sunkiss", "Sunnybrook", "Sunrise", "Sunshine", "Supertramp", "Sushi", "Sussudio", "Swami", "Swampy", "Sweathog", "SweetCheeks", "SweetPea", "Sweetheart", "Sweetie", "Sweetness", "Swizzle", "Symphony", "Synergy", "TnT", "Taboo", "Tabriz", "Taffy", "Tag", "Tahiti", "Talahache", "Talisman", "Tallyho", "Tamahto", "Tamara", "Tampest", "Tang", "Tango", "Tank", "Tanka", "Tanner", "Tao", "Taos", "Tar", "Tara", "Targa", "Taro", "Tarot", "Tarragon", "Tartine", "Tartuffe", "Tarzan", "Tatters", "Tattletale", "Tattoo", "Taurus", "Tauskey", "Taxi", "Taz", "Tazer", "Tbone", "Teacup", "Teal", "Tease", "Tennessee", "Tequila", "Terminator", "TerraCotta", "Terror", "Tess", "Testarossa", "Texas", "Thai", "Thatcher", "Thistle", "Thor", "Thorn", "Thudpaw", "Thumper", "Thunder", "Thyme", "TiAmo", "Tiara", "TieDye", "Tiger", "Tigger", "Tilly", "Tinker", "Tinkerbell", "Tinkertoy", "TinkleBell", "Tinman", "Tinsel", "Tiny", "Tipsy", "Tissot", "Titan", "Titian", "Tizz", "Tizzy", "Toad", "Toby", "Tofu", "Tomahawk", "Tomba", "TomTom", "Tonto", "Tooter", "Tootsie", "Topaz", "Toreador", "Tornado", "Toro", "Torts", "Toto", "Touche", "Tova", "Toybox", "Trailblazer", "Tramp", "Treat", "Trekky", "Tricky", "Trilogy", "Trinket", "Trip", "Trixie", "Trojan", "Troll", "Tropicana", "Troubadour", "Trouble", "Trouper", "Trout", "Truffaut", "Trufflehunter", "Truffles", "Trump", "Trurman", "Tryst", "Tsunami", "Tubbo", "Tubby", "Tubs", "Tuffy", "Tugboat", "Tulip", "Tumbleweed", "Tuna", "Tundra", "Turbo", "Turkey", "TuttiFrutti", "Tutu", "Tweety", "Twerp", "Twinkie", "Twinkle", "Twister", "Tycoon", "Typhoon", "Udo", "Ufo", "UglyBugly", "Ulysses", "Ulanova", "Uma", "UmpaLumpa", "Unagi", "Urchin", "Uriel", "Ursa", "Ursula", "Ushki", "Utopia", "Vaca", "Vagabond", "Valentine", "Valentino", "VanGogh", "VanDamme", "Vanilla", "Vaquero", "Veggie", "Velcro", "Velvet", "Venus", "Verona", "Vichyssoise", "Violet", "Viper", "Visa", "Viva", "Vixen", "Voltaire", "Voodoo", "Voyager", "VroomVroom", "Wacky", "Waddles", "Wagalot", "Wags", "Waldo", "Wallflower", "Walrus", "Wanda", "Warhol", "Warlock", "Warrior", "Warthog", "Wasabe", "Web", "Wellington", "Whirlwind", "Whiskey", "Whisper", "Whisperwind", "Whoopie", "Wiggles", "Wilbur", "WildThing", "Wildwood", "Wilfred", "Wilhelmina", "Willoughby", "Willow", "Wilson", "Wimpy", "Windjammer", "Windsor", "Windy", "Wink", "Winston", "Winthrop", "Wiseguy", "Witch", "Wizard", "Wizkid", "Wolfgang", "Wonderdog", "Wonton", "Woodstock", "Woodwind", "Woody", "Woof", "Wookie", "Wooly", "Wrangler", "Wrigley", "Xanadu", "Xanthus", "Xavier", "Xena", "Xerox", "Xerxes", "YabbaDabbaDoo", "Yahoo", "Yamasee", "Yankee", "Yaya", "Yeager", "Yorgy", "Yosemite", "YouToo", "YoYo", "Yukon", "Yule", "Yuma", "Zack", "Zaire", "Zebu", "Zeke", "Zelda", "Zelig", "Zepellin", "Zephyr", "Zeppo", "Zero", "Zeus", "Ziggy", "ZippityDoDa", "Zodiac", "Zoe", "Zombie", "Zooey", "ZoomZoom", "Zoppico", "Zorro", "ZsaZsa", "Zulu", "Zuni"));
    private final ArrayList<String> dogSoloQsuote = new ArrayList<>(Arrays.asList("wif", "waf", "woof", "wtf", "kai", "barf", "borf", "bwarf", "gr", "grr", "grrr", "grrrr", "graou", "miaou", "waof", "bark", "squik"));
    
    private final int ageMax = 20;
    private final double minWeight = 1;
    private final double maxWeight = 40;
    private final int minWordInQuote = 1;
    private final int maxWordInQuote = 10;

    final int dogNameSize = dogNames.size();
    final int dogSoloQuoteSize = dogSoloQsuote.size();

    private final Random rand = new Random();

    /**
     * Geneère un nombre aléatoire de chien
     * 
     * @param number le nombre de chien à générer
     * @return la liste des chiens qu'on a généré
     */
    @Override
    public List<Dog> generateDog(int number) {

        List<Dog> newDogs = new ArrayList<>();

        String nom;
        int age;
        double poids;
        String quote;

        for (int i = 0; i < number; ++i) {
            nom = dogNames.get(rand.nextInt(dogNameSize));
            age = rand.nextInt(ageMax);
            poids = round((Math.random() * (maxWeight - minWeight) + minWeight), 1);
            quote = generateQuote();
            newDogs.add(new Dog(nom, age, poids, quote));
        }

        return newDogs;
    }

    /**
     * Incroyable fonction qui permet de construire des quotes incroyables
     * 
     * @return Des quotes plus amusantes les unes que les autres
     */
    private String generateQuote() {
        int nbWordInQuote = (int) (Math.random() * (maxWordInQuote - minWordInQuote)) + minWordInQuote;

        List<String> quote = new ArrayList<>();
        int check = 0;
        while (check < nbWordInQuote) {
            int nb = (int) (Math.random() * (nbWordInQuote - check - 1)) + 1;
            String tmp = "";
            for (int i = 0; i < nb; i++) {
                tmp += dogSoloQsuote.get(rand.nextInt(dogSoloQuoteSize));
            }

            quote.add(tmp);

            check += nb;
        }

        return String.join(" ", quote);
    }

    /**
     * Fonction utile qui permet d'arrondire un double avec une précision donnée
     * 
     * @param value Le double à arrondire
     * @param precision La précison désirée
     * @return Le double arrondi
     */
    private double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
}
