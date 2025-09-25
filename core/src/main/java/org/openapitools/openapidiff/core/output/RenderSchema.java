package org.openapitools.openapidiff.core.output;

import java.io.IOException;
import java.io.OutputStreamWriter;
import org.openapitools.openapidiff.core.exception.RendererException;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;

public abstract class RenderSchema implements Render {
  @Override
  public void render(ChangedOpenApi diff, OutputStreamWriter outputStreamWriter)
      throws RendererException {
    renderDocumentStart(diff, outputStreamWriter);

    renderTitle(diff, outputStreamWriter);
    renderMainSectionStart(diff, outputStreamWriter);

    renderAddedEndpoints(diff.getA);
    renderRemovedEndpoints();
    renderDeprecatedEndpoints();
    renderChanged();

    renderMainSectionEnd(diff, outputStreamWriter);

    renderDocumentEnd(diff, outputStreamWriter);

    try {
      outputStreamWriter.close();
    } catch (IOException e) {
      throw new RendererException(e);
    }
  }

  protected void renderDocumentEnd(ChangedOpenApi diff, OutputStreamWriter outputStreamWriter) {
    // by default do nothing
  }

  protected void renderDocumentStart(ChangedOpenApi diff, OutputStreamWriter outputStreamWriter) {
    // by default do nothing
  }

  protected abstract void renderTitle(ChangedOpenApi diff, OutputStreamWriter outputStreamWriter);
}
