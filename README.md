# Movies app

## Building

Before building the app you need to create `secrets.properties` file in the root directory of the
project. Inside of this file put your API key from TMDB. You can check how the file should look like
in the example file - `local.defaults.properties`.

## Architecture

As this is small app I decided to simply divide it into separate folders instead of creating feature
modules.
In `data` everything related to remote data sources is placed - data sources, DTO models and
implementation of repositories.
In `domain` are models that should be used by business logic, interfaces of repositories and would
put
there also use cases if I had any repetitive logic I want to share between view models.
In `presentation` there are view models and UI, also presentation models if screen needs it
In `di` there are hilt modules that inject everything that is not implemented in the app itself,
like okhttp instance etc

## Libraries

- `navigation-compose` - to create navigation graph using only composables
- `kotlinx-serialization-json` - json serializer used by retrofit and navigation library
- `hilt` - wrapper around dagger (nicely wrapping it into android lifecycles), used for dependency
  injection
- `retrofit` - my favourite http client wrapper that generates all the code required to make api
  calls based on simple interfaces
- `coil` - to display images from network
