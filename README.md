# Open-Rails-ATP

## What is this?

This is a Java implementation of Alstom's [ATP](https://es.wikipedia.org/wiki/Automatic_Train_Protection) system used in the B subway line of Buenos Aires. It was developed using Spring for the backend and React for the frontend. It's intended to be used with an extension like [Flobro](https://flobro.app/) which allows it to seamlessly integrate with [Open Rails](http://openrails.org/), and communicates with it by using its web API.

## What's its current state?

- The base system *works*
- The development of this program has been put on hold until a couple of bugs are fixed in OR's API.
- The program in its current state can warn the user that an emergency brake state should be forced, but doesn't do so due to API limitations (I have some ideas for workarounds but it wouldn't make sense applying them until the previously mentioned bug is fixed)
- The braking curve should be adjusted to more precisely match the real system
- There are some other inaccuracies, but information about the real deal is almost non-existent

## What did you learn in this project?

It was another excuse to develop a frontend using React and a backend using Java/Spring. I did this project because I really wanted to have that ATP system in-game. I also have a particular interest for such systems, so you know what they say: "do a project about something you actually like". This is one of *those* projects for me. It was an interesting experience learning about the OR API (I didn't initially have it in my plans) and the real system. I used Figma to create the base ["texture"](https://github.com/Matias-Rossi/Open-Rails-ATP/blob/main/OR-ATP-Linea-B-Frontend/src/assets/images/ATS_Generado.png), basing the design on a photo found on the internet.
