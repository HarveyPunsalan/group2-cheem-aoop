-- Insert predefined action types into the user_action table
-- Each action represents a distinct operation allowed in the system
-- These will later be linked to access and roles

INSERT INTO 
  user_action (action_name, description) 

VALUES
  -- Basic CRUD operations
  ('create', 'Create operation on system resource'),
  ('view', 'Read/list operation on system resource'),
  ('update', 'Modify existing system resource'),
  ('delete', 'Remove or deactivate a resource'),

  -- Workflow-based approval operations
  ('approve', 'Authorize a pending workflow action'),
  ('reject', 'Deny a pending workflow action'),

  -- Submission and cancellation actions
  ('submit', 'Submit form or request to the system'),
  ('cancel', 'Cancel an active or pending record'),

  -- Assignment and admin privileges
  ('assign', 'Assign user roles or entities'),
  ('generate', 'Generate reports or documents'),

  -- File operations
  ('upload', 'Upload file or document'),
  ('download', 'Download file or document'),

  -- System tools and utilities
  ('reset', 'Reset credentials or default state'),
  ('export', 'Export data to a specified format'),
  ('configure', 'Modify application/system configuration'),
  ('backup', 'Backup system data'),
  ('restore', 'Restore system data from backup');
