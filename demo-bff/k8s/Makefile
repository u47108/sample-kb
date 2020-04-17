build:
	kubectl create -f deployment.yml
	kubectl create -f service.yml
	kubectl describe service custommvc-service

	minikube service custommvc-service

rebuild:
	kubectl replace -f deployment.yml
	kubectl delete -f service.yml
	kubectl create -f service.yml
	kubectl describe service custommvc-service

	minikube service custommvc-service

