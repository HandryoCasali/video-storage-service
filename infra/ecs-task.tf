
resource "aws_ecs_task_definition" "storage_service_task" {
  family                   = "storage-service"
  requires_compatibilities = ["FARGATE"]
  network_mode             = "awsvpc"
  cpu                      = "512"
  memory                   = "1024"
  execution_role_arn       = var.labsrole_arn
  task_role_arn            = var.labsrole_arn
  depends_on = [
    aws_cloudwatch_log_group.ecs_storage_service
  ]

  container_definitions = jsonencode([
    {
      name      = "storage-service"
      image     = var.image_url
      essential = true
      portMappings = [{
        containerPort = 8080
        protocol      = "tcp"
      }]
      environment = [
        {
          name  = "SPRING_PROFILES_ACTIVE"
          value = var.spring_profile
        },
        {
          name  = "SQS_QUEUE_NAME"
          value = var.sqs_s3_object_created_name
        },
        {
          name  = "S3_BUCKET_NAME"
          value = var.s3_bucket_name
        },
        {
          name = "VIDEO_MANAGEMENT_URL"
          value = var.video_management_url
        }
      ]
      logConfiguration = {
        logDriver = "awslogs"
        options = {
          awslogs-group         = "/ecs/storage-service"
          awslogs-region        = var.region
          awslogs-stream-prefix = "ecs"
        }
      }
    }
  ])
  lifecycle {
    create_before_destroy = true
  }
}

resource "aws_ecs_service" "storage_service" {
  name            = "storage-service"
  cluster         = data.aws_ecs_cluster.video_cluster.id
  task_definition = aws_ecs_task_definition.storage_service_task.arn
  launch_type     = "FARGATE"
  desired_count   = 1

  network_configuration {
    subnets         = var.subnet_ids
    security_groups = [aws_security_group.app_sg.id]
    assign_public_ip = false
  }

  load_balancer {
    target_group_arn = aws_lb_target_group.storage_service_tg.arn
    container_name   = "storage-service"
    container_port   = 8080
  }

  depends_on = [aws_lb_listener_rule.storage_service_rule]
}
