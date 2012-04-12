package thinwire.apps.test;

import thinwire.render.web.WebApplication;

public class ClientSideIncludeTest {
	public static void main(String[] args) {
		WebApplication app = (WebApplication)WebApplication.current();
		app.clientSideIncludeFile("class:///" + ClientSideIncludeTest.class.getName() + "/resources/ClientSideInclude1.js");
		app.clientSideIncludeFile("class:///" + ClientSideIncludeTest.class.getName() + "/resources/ClientSideInclude2.js");
		app.clientSideFunctionCall("sayIt", "Dude!");
		app.clientSideFunctionCall("sayItAgain", "Dude Again!");
	}
}
