package code.menu

import code.gfx.*
import code.sound.Sound

class TitleMenu: Menu() {
	private var selected = 0 // Currently selected option
	
	companion object {
		private val options = arrayOf("Start game", "How to play", "About", "Quit") // Options that are on the main menu, each seperated by a comma.
	}
	
	override fun tick() {
		if (input.up.clicked) selected-- // If the player presses the up key, then move up 1 option in the list
		if (input.down.clicked) selected++ // If the player presses the down key, then move down 1 option in the list
		
		val len = options.size // The size of the list (normally 4 options)
		if (selected < 0) selected += len // If the selected option is less than 0, then move it to the last option of the list.
		if (selected >= len) selected -= len // If the selected option is more than or equal to the size of the list, then move it back to 0;
		
		if (input.attack.clicked || input.menu.clicked) { //If either the "Attack" or "Menu" keys are pressed then...
			when (selected) {
				0 -> { /*If the selection is 0 ("Start game")*/
					Sound.test.play() //Play a sound
					game.resetGame() //Reset the game
					game.setMenu(null) //Set the menu to null (No menus active)
				}
				1 -> game.setMenu(InstructionsMenu(this)) //If the selection is 1 ("How to play") then go to the instructions menu.
				2 -> game.setMenu(AboutMenu(this)) //If the selection is 2 ("About") then go to the about menu.
				3 -> {
					println("You have decided to quit\n" + "The average FPS was : " + game.totalFrames / (game.gameTime / 60) + '\n' + "The total time was : " + game.gameTime / 60 + '\n')
					System.exit(0)
				}
			}
		}
	}
	
	override fun render(screen: Screen) {
		screen.clear()// Clears the screen to a black color.
		
		/* This section is used to display the minicraft title */
		
		val h = 2 // Height of squares (on the spritesheet)
		val w = 11 // Width of squares (on the spritesheet)
		val titleColour = Colour(0x0, 0x0, 0x0f0, 0x0) // Colour of the Title
		val xo = (screen.w - w * 8) / 2 // X location of the title
		val yo = 24 // Y location of the title
		for (y in 0 until h) {
			for (x in 0 until w) {
				screen.render(xo + x * 8, yo + y * 8, Sprite0x6(x + 1, y + 6, Inversion.NONE, titleColour)) // Loops through all the squares to render them all on the screen.
			}
		}
		
		/* This section is used to display this options on the screen */
		
		for (i in options.indices) { // Loops through all the options in the list
			var msg = options[i] // Text of the current option
			var colour = Colour(0x0, -1, -1, 0xaaa) // Colour of unselected text
			if (i == selected) { // If the current option is the option that is selected
				msg = "> $msg <" // Add the cursors to the sides of the message
				colour = Colour(0x0, -1, -1, 0x00f) // change the colour of the option
			}
			Font.draw(msg, screen, (screen.w - msg.length * 8) / 2, (8 + i) * 8, colour)
		}
		
		Font.draw("(Arrow keys,X and C)", screen, 0, screen.h - 8, Colour(0x0, 0x444, 0x444, 0x444)) // Draw text at the bottom
	}
}