DROP TABLE movie;

CREATE TABLE movie (
  title TEXT PRIMARY KEY,
  year TEXT,
  released TEXT,
  runtime TEXT,
  actor TEXT,
  plot TEXT,
  genre TEXT,
  rated TEXT );

INSERT INTO movie VALUES
   ('The Ugly Truth','2009','24 July 2009','96 min','Katherine Heigl, Gerard Butler, Bree Turner, Eric Winter','A romantically challenged morning show producer is reluctantly embroiled in a series of outrageous tests by her chauvinistic correspondent to prove his theories on relationships ..','Comedy','R');
INSERT INTO movie VALUES
   ('Interstellar','2014','07 Nov 2014','169 min','Ellen Burstyn, Matthew McConaughey, Mackenzie Foy, John Lithgow','A team of explorers travel through a wormhole in space in an attempt to ensure humanity survival.','Action','PG-13');
INSERT INTO movie VALUES
   ('50 First Dates','2004','13th feb 2014','106 min','Adam Sandler, Drew Barrymore, Rob Schneider, Sean Astin','Henry Roth is a man afraid of commitment up until he meets the beautiful Lucy. They hit it off and Henry think he finally found the girl of his dreams, until he discovers she has short-term memory loss and forgets him the very next day.','Comedy','PG-13');
INSERT INTO movie VALUES
    ('Intern','2015','18th july 2014','106 min','Robert De Niro, Anne Hathaway, Rene Russo','While working by night, a male intern is suddenly aware of some strange sound that comes from upstairs. However, a female intern who works with him doesn''t hear that sound at all.','Comedy','R');
INSERT INTO movie VALUES
    ('Cassablanca','1943','23rd Jan 1943','106 min','Humphrey Bogart, Ingrid Bergman, Paul Henreid, Claude Rains','Set in Casablanca, Morocco during the early days of World War II: An American expatriate meets a former lover, with unforeseen complications.','Drama','PG');
INSERT INTO movie VALUES
    ('Kung Fu Panda','2008','06 June 2008','92 min','Jack Black, Dustin Hoffman, Angelina Jolie, Ian McShane','In the Valley of Peace, Po the Panda finds himself chosen as the Dragon Warrior despite the fact that he is obese and a complete novice at martial arts.','Animation','PG');
INSERT INTO movie VALUES
    ('Forrest Gump','1994','06 july 2014','142 min','Tom Hanks, Rebecca Williams, Sally Field, Michael Conner Humphreys','Forrest Gump, while not intelligent, has accidentally been present at many historic moments, but his true love, Jenny Curran, eludes him.','Animation','PG-13');
INSERT INTO movie VALUES
    ('How to Train Your Dragon','2010','26 Mar 2010','106 min','Jay Baruchel, Gerard Butler, Craig Ferguson, America Ferrera','A hapless young Viking who aspires to hunt dragons, becomes the unlikely friend of a young dragon himself, and learns there may be more to the creatures than he assumed.','Comedy','PG');