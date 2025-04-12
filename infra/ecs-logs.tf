resource "aws_cloudwatch_log_group" "ecs_storage_service" {
  name              = "/ecs/storage-service"
  retention_in_days = 7
}