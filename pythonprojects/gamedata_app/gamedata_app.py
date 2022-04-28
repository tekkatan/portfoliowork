import csv

def new_game_ID():
	"""Make a new game ID and write data to csv"""
	# Check if file exists
	try:
		with open(filename,"r",newline='')as hero_file:
			print("Searching file...")
	except FileNotFoundError:
		print("File not found, this application will be closed.")
	else:
		# Ask user for data about the game
		title=input("Name of the game: ")
		name=input("Gamecharacter's name: ")
		good_bad=input("Good or bad:  ")
		gender=input("Gender gamecharacter? (male/female) ")
		price=input("Price of the game: ")
		# Adding data to the file
		with open(filename,"a",newline='')as hero_file:
			hero_fileWriter=csv.writer(hero_file,delimiter=',')
			hero_fileWriter.writerow([title,name,good_bad,gender,price])
		# A summary of the data that has been added
		print("\nYou've added: title: "+title+
		","+name+","+good_bad+","+gender+","+price)
		
def search_gametitle_gender():
	"""Searches in the filename for the gametitle and gender"""
	with open(filename,"r")as hero_file:
		gametitle_searchvalue=input("Fill in game title ")
		gender_searchvalue=input("Fill in gender (male/female)")
		hero_fileReader=csv.reader(hero_file,delimiter=',')
		for row in hero_fileReader:
			if row[0]==gametitle_searchvalue and row[3]==gender_searchvalue:
				print(row)
				
def search_name_character():
	"""Searches in the filename by charactername"""
	with open(filename,"r")as hero_file:
		name_searchvalue=input("Fill in charctername")
		hero_fileReader=csv.reader(hero_file,delimiter=',')
		for row in hero_fileReader:
			if row[1]==name_searchvalue:
				print(row)
				
def search_gender():
	"""Searches by gender game character"""
	with open(filename,"r")as hero_file:
		gender_searchvalue=input("Fill in (male of female): ")
		hero_fileReader=csv.reader(hero_file,delimiter=',')
		for row in hero_fileReader:
			if row[3]==gender_searchvalue:
				print(row)
		
filename="game_heroes.csv"
new_game_ID()


