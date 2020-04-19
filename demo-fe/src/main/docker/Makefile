include env_make

NS = ehms
REPO = demo-bff
VERSION = latest
NAME = demo-bff

all: build run

mvn-build:
	cd ..; \
	mvn clean install

build: mvn-build
	cp .././target/$(FINAL_NAME) .
	docker build -t $(NS)/$(REPO):$(VERSION) .
	rm ./$(FINAL_NAME)

run:
	docker run -d $(PORTS) --name $(NAME) $(NS)/$(REPO):$(VERSION)

start:
	docker start $(NAME)

stop:
	docker stop $(NAME)

rm: stop
	docker rm $(NAME)

rebuild: stop rm build run

push:
	docker login
	docker push $(NS)/$(REPO)